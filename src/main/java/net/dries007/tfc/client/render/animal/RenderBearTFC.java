/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.render.animal;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.client.model.animal.ModelBearTFC;
import net.dries007.tfc.objects.entity.animal.EntityBearTFC;

import static net.dries007.tfc.api.util.TFCConstants.MOD_ID;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class RenderBearTFC extends RenderLiving<EntityBearTFC>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/animal/bear.png");

    public RenderBearTFC(RenderManager renderManager)
    {
        super(renderManager, new ModelBearTFC(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBearTFC entity)
    {
        return TEXTURE;
    }
}