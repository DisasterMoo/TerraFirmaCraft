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

import net.dries007.tfc.client.model.animal.ModelPolarBearTFC;
import net.dries007.tfc.objects.entity.animal.EntityPolarBearTFC;

import static net.dries007.tfc.api.util.TFCConstants.MOD_ID;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class RenderPolarBearTFC extends RenderLiving<EntityPolarBearTFC>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/animal/polarbear.png");

    public RenderPolarBearTFC(RenderManager renderManager)
    {
        super(renderManager, new ModelPolarBearTFC(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPolarBearTFC entity)
    {
        return TEXTURE;
    }
}