/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.render.animal;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.api.types.IAnimalTFC;
import net.dries007.tfc.client.model.animal.ModelLionTFC;
import net.dries007.tfc.objects.entity.animal.EntityAnimalTFC;
import net.dries007.tfc.objects.entity.animal.EntityLionTFC;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class RenderLionTFC extends RenderLiving<EntityLionTFC>
{
    private static final ResourceLocation LION_YOUNG = new ResourceLocation(MOD_ID, "textures/entity/animal/lion_young.png");
    private static final ResourceLocation LION_OLD = new ResourceLocation(MOD_ID, "textures/entity/animal/lion_old.png");

    private static final ResourceLocation LIONESS_YOUNG = new ResourceLocation(MOD_ID, "textures/entity/animal/lioness_young.png");
    private static final ResourceLocation LIONESS_OLD = new ResourceLocation(MOD_ID, "textures/entity/animal/lioness_old.png");


    public RenderLionTFC(RenderManager manager)
    {
        super(manager, new ModelLionTFC(), 0.3F);
    }

    @Override
    public void doRender(EntityLionTFC lion, double par2, double par4, double par6, float par8, float par9)
    {
        this.shadowSize = (float) (0.4f + lion.getPercentToAdulthood() * 0.4f);
        super.doRender(lion, par2, par4, par6, par8, par9);
    }

    
    protected ResourceLocation getEntityTexture(EntityLionTFC lion)
    {
        float percent = (float) lion.getPercentToAdulthood();

        if (lion.getGender() != EntityAnimalTFC.Gender.MALE || percent < .75f)
        {
            return lion.getAge() == IAnimalTFC.Age.OLD ? LIONESS_OLD : LIONESS_YOUNG;
        }
        else
        {
            return lion.getAge() == IAnimalTFC.Age.OLD ? LION_OLD : LION_YOUNG;
        }
    }


}