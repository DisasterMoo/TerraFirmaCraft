/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.common.blocks.soil.TFCGrassBlock;
import net.dries007.tfc.common.types.Rock;
import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.world.chunkdata.ChunkData;
import net.dries007.tfc.world.chunkdata.RockData;

/**
 * Replaces world gen blocks with TFC equivalents.
 * This allows us to use minecraft blocks for the majority of early world gen, which works much better with vanilla systems such as surface builders.
 *
 * This structure is necessary in order to not bastardize the vanilla generation pipeline
 */
public class ChunkBlockReplacer
{
    private static final Logger LOGGER = LogManager.getLogger();

    protected final Map<Block, IBlockReplacer> replacements;

    public ChunkBlockReplacer()
    {
        replacements = new HashMap<>();
        registerDefaultReplacements();
    }

    public void replace(IWorld worldGenRegion, IChunk chunk, Random random, ChunkData data)
    {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        int xStart = chunk.getPos().getXStart();
        int zStart = chunk.getPos().getZStart();
        RockData rockData = data.getRockData();
        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
                float temperature = data.getAverageTemp(x, z);
                float rainfall = data.getRainfall(x, z);
                float noise = random.nextFloat() - random.nextFloat(); // One simple "gaussian" noise value per column

                for (int y = 0; y <= chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE_WG, x, z); y++)
                {
                    pos.setPos(xStart + x, y, zStart + z);

                    // Base replacement
                    BlockState stateAt = chunk.getBlockState(pos);
                    IBlockReplacer replacer = replacements.get(stateAt.getBlock());
                    if (replacer != null)
                    {
                        stateAt = replacer.getReplacement(rockData, x, y, z, rainfall, temperature, noise);
                        chunk.setBlockState(pos, stateAt, false);
                        replacer.updatePostPlacement(worldGenRegion, pos, stateAt);
                    }
                }
            }
        }
    }

    public void register(Block block, IBlockReplacer replacer)
    {
        if (replacements.containsKey(block))
        {
            LOGGER.debug("Replaced entry {} in ChunkBlockReplacer", block);
        }
        replacements.put(block, replacer);
    }

    protected void registerDefaultReplacements()
    {
        // Stone -> raw rock
        register(Blocks.STONE, (rockData, x, y, z, rainfall, temperature, random) -> {
            if (y < rockData.getRockHeight(x, z))
            {
                return rockData.getBottomRock(x, z).getBlock(Rock.BlockType.RAW).getDefaultState();
            }
            else
            {
                return rockData.getTopRock(x, z).getBlock(Rock.BlockType.RAW).getDefaultState();
            }
        });

        // Dirt -> under surface material. Replaced with dirt, or inland sand (deco rock layer)
        register(Blocks.DIRT, (rockData, x, y, z, rainfall, temperature, noise) -> getSoilBlock(SoilBlockType.DIRT, rockData, x, z, rainfall, noise));

        // Grass -> top layer surface material (grass in high rainfall, sand in low rainfall)
        register(Blocks.GRASS_BLOCK, new IBlockReplacer()
        {
            @Override
            public BlockState getReplacement(RockData rockData, int x, int y, int z, float rainfall, float temperature, float noise)
            {
                return getSoilBlock(SoilBlockType.GRASS, rockData, x, z, rainfall, noise);
            }

            @Override
            public void updatePostPlacement(IWorld world, BlockPos pos, BlockState state)
            {
                if (state.getBlock() instanceof TFCGrassBlock)
                {
                    world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 0);
                }
            }
        });

        // Gravel -> surface material. Replace with rock type gravel
        register(Blocks.GRAVEL, (rockData, x, y, z, rainfall, temperature, random) -> rockData.getTopRock(x, z).getBlock(Rock.BlockType.GRAVEL).getDefaultState());

        // Sand -> Desert sand layer. Replace with sand color from top rock layer
        register(Blocks.SAND, (rockData, x, y, z, rainfall, temperature, noise) -> TFCBlocks.SAND.get(rockData.getTopRock(x, z).getDesertSandColor()).get().getDefaultState());

        // Red Sand -> Beach sand layer. Replace with the beach sand color from top rock layer
        register(Blocks.RED_SAND, (rockData, x, y, z, rainfall, temperature, noise) -> TFCBlocks.SAND.get(rockData.getMidRock(x, z).getBeachSandColor()).get().getDefaultState());

        // Red Sandstone -> Beach variant sand layer. If tropical, replace with pink sand.
        register(Blocks.RED_SANDSTONE, (rockData, x, y, z, rainfall, temperature, noise) -> {
            if (rainfall > 300f && temperature > 15f)
            {
                return TFCBlocks.SAND.get(SandBlockType.PINK).get().getDefaultState();
            }
            else if (rainfall < 300f)
            {
                return TFCBlocks.SAND.get(SandBlockType.BLACK).get().getDefaultState();
            }
            else
            {
                return TFCBlocks.SAND.get(rockData.getMidRock(x, z).getBeachSandColor()).get().getDefaultState();
            }
        });
    }

    private BlockState getSoilBlock(SoilBlockType soil, RockData rockData, int x, int z, float rainfall, float noise)
    {
        if (rainfall < TFCConfig.COMMON.sandRainfallCutoff.get() + TFCConfig.COMMON.sandRainfallRange.get() * noise)
        {
            return TFCBlocks.SAND.get(rockData.getTopRock(x, z).getDesertSandColor()).get().getDefaultState();
        }
        else if (rainfall < TFCConfig.COMMON.sandyLoamRainfallCutoff.get() + TFCConfig.COMMON.sandyLoamRainfallRange.get() * noise)
        {
            return TFCBlocks.SOIL.get(soil).get(SoilBlockType.Variant.SANDY_LOAM).get().getDefaultState();
        }
        else if (rainfall < TFCConfig.COMMON.siltyLoamRainfallCutoff.get() + TFCConfig.COMMON.siltyLoamRainfallRange.get() * noise)
        {
            return TFCBlocks.SOIL.get(soil).get(SoilBlockType.Variant.SILTY_LOAM).get().getDefaultState();
        }
        else
        {
            return TFCBlocks.SOIL.get(soil).get(SoilBlockType.Variant.SILT).get().getDefaultState();
        }
    }
}