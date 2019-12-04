/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBed.EnumPartType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.dries007.tfc.objects.entity.animal.EntityOcelotTFC;

public class EntityAIOcelotSitTFC extends EntityAIMoveToBlock
{
    private final EntityOcelotTFC ocelot;

    public EntityAIOcelotSitTFC(EntityOcelotTFC ocelotIn, double p_i45315_2_)
    {
        super(ocelotIn, p_i45315_2_, 8);
        this.ocelot = ocelotIn;
    }

    public boolean shouldExecute()
    {
        return this.ocelot.isTamed() && !this.ocelot.isSitting() && super.shouldExecute();
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.ocelot.getAISit().setSitting(false);
    }

    public void updateTask()
    {
        super.updateTask();
        this.ocelot.getAISit().setSitting(false);
        if (!this.getIsAboveDestination())
        {
            this.ocelot.setSitting(false);
        }
        else if (!this.ocelot.isSitting())
        {
            this.ocelot.setSitting(true);
        }

    }

    protected boolean shouldMoveTo(World worldIn, BlockPos pos)
    {
        if (!worldIn.isAirBlock(pos.up()))
        {
            return false;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            if (block == Blocks.CHEST)
            {
                TileEntity tileentity = worldIn.getTileEntity(pos);
                return tileentity instanceof TileEntityChest && ((TileEntityChest) tileentity).numPlayersUsing < 1;
            }
            else
            {
                if (block == Blocks.LIT_FURNACE)
                {
                    return true;
                }

                return block == Blocks.BED && iblockstate.getValue(BlockBed.PART) != EnumPartType.HEAD;
            }

        }
    }

    public void resetTask()
    {
        super.resetTask();
        this.ocelot.setSitting(false);
    }
}