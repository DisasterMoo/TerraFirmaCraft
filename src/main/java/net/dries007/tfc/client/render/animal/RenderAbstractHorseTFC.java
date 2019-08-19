/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.render.animal;

import java.util.Map;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.client.model.animal.ModelHorseTFC;
import net.dries007.tfc.objects.entity.animal.AbstractHorseTFC;
import net.dries007.tfc.objects.entity.animal.EntityDonkeyTFC;
import net.dries007.tfc.objects.entity.animal.EntityMuleTFC;

@SideOnly(Side.CLIENT)
public class RenderAbstractHorseTFC extends RenderLiving<AbstractHorseTFC>
{
    private static final Map<Class<?>, ResourceLocation> MAP = Maps.newHashMap();

    static
    {
        MAP.put(EntityDonkeyTFC.class, new ResourceLocation("textures/entity/horse/donkey.png"));
        MAP.put(EntityMuleTFC.class, new ResourceLocation("textures/entity/horse/mule.png"));
    }

    private final float scale;

    public RenderAbstractHorseTFC(RenderManager manager)
    {
        this(manager, 1.0F);
    }

    public RenderAbstractHorseTFC(RenderManager renderManagerIn, float scaleIn)
    {
        super(renderManagerIn, new ModelHorseTFC(), 0.75F);
        this.scale = scaleIn;
    }

    protected void preRenderCallback(AbstractHorseTFC entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(this.scale, this.scale, this.scale);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    protected ResourceLocation getEntityTexture(AbstractHorseTFC entity)
    {
        return MAP.get(entity.getClass());
    }
}
