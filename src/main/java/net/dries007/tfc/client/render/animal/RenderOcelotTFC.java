/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.render.animal;


import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.client.model.animal.ModelOcelotTFC;
import net.dries007.tfc.objects.entity.animal.EntityOcelotTFC;

import static net.dries007.tfc.api.util.TFCConstants.MOD_ID;

@SideOnly(Side.CLIENT)
public class RenderOcelotTFC extends RenderLiving<EntityOcelotTFC>
{
    private static final ResourceLocation BLACK_OCELOT_TEXTURES = new ResourceLocation(MOD_ID, "textures/entity/animal/cat/black.png");
    private static final ResourceLocation OCELOT_TEXTURES = new ResourceLocation(MOD_ID, "textures/entity/animal/cat/ocelot.png");
    private static final ResourceLocation RED_OCELOT_TEXTURES = new ResourceLocation(MOD_ID, "textures/entity/animal/cat/red.png");
    private static final ResourceLocation SIAMESE_OCELOT_TEXTURES = new ResourceLocation(MOD_ID, "textures/entity/animal/cat/siamese.png");

    public RenderOcelotTFC(RenderManager p_i47199_1_) {
        super(p_i47199_1_, new ModelOcelotTFC(), 0.4F);
    }

    protected ResourceLocation getEntityTexture(EntityOcelotTFC entity) {
        switch(entity.getTameSkin()) {
            case 0:
            default:
                return OCELOT_TEXTURES;
            case 1:
                return BLACK_OCELOT_TEXTURES;
            case 2:
                return RED_OCELOT_TEXTURES;
            case 3:
                return SIAMESE_OCELOT_TEXTURES;
        }
    }

    protected void preRenderCallback(EntityOcelotTFC entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        if (entitylivingbaseIn.isTamed()) {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }

    }
}
