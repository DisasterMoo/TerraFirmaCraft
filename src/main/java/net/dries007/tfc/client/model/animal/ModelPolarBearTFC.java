/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.model.animal;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.objects.entity.animal.EntityPolarBearTFC;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class ModelPolarBearTFC extends ModelQuadruped
{
    private ModelRenderer head;
    private ModelRenderer body;

    public ModelPolarBearTFC()
    {
        super(12, 0.0F);
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F);
        this.head.setRotationPoint(0.0F, 10.0F, -16.0F);
        this.head.setTextureOffset(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F);
        this.head.setTextureOffset(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        ModelRenderer modelrenderer = this.head.setTextureOffset(26, 0);
        modelrenderer.mirror = true;
        modelrenderer.addBox(2.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        this.body = new ModelRenderer(this);
        this.body.setTextureOffset(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
        this.body.setTextureOffset(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
        this.body.setRotationPoint(-2.0F, 9.0F, 12.0F);

        this.leg1 = new ModelRenderer(this, 50, 22);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg1.setRotationPoint(-3.5F, 14.0F, 6.0F);
        this.leg2 = new ModelRenderer(this, 50, 22);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg2.setRotationPoint(3.5F, 14.0F, 6.0F);
        this.leg3 = new ModelRenderer(this, 50, 40);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg3.setRotationPoint(-2.5F, 14.0F, -7.0F);
        this.leg4 = new ModelRenderer(this, 50, 40);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg4.setRotationPoint(2.5F, 14.0F, -7.0F);
        --this.leg1.rotationPointX;
        ++this.leg2.rotationPointX;
        ModelRenderer var10000 = this.leg1;
        var10000.rotationPointZ += 0.0F;
        var10000 = this.leg2;
        var10000.rotationPointZ += 0.0F;
        --this.leg3.rotationPointX;
        ++this.leg4.rotationPointX;
        --this.leg3.rotationPointZ;
        --this.leg4.rotationPointZ;
        this.childZOffset += 2.0F;
    }

    @Override
    public void render(@Nonnull Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, entity);

        EntityPolarBearTFC polarbear = ((EntityPolarBearTFC) entity);

        float percent = (float) polarbear.getPercentToAdulthood();

        float ageScale = 2.0F - percent;
        float ageHeadScale = (float) Math.pow(1 / ageScale, 0.66);
        GlStateManager.pushMatrix();

        GlStateManager.translate(0.0F, 0.75f - (0.75f * percent), 0f);
        GlStateManager.scale(ageHeadScale, ageHeadScale, ageHeadScale);
        GlStateManager.translate(0.0F, 0, 0.1875f - (0.1875f * percent));

        head.render(par7);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.75f - (0.75f * percent), 0f);
        GlStateManager.scale(1 / ageScale, 1 / ageScale, 1 / ageScale);

        body.render(par7);
        leg1.render(par7);
        leg2.render(par7);
        leg3.render(par7);
        leg4.render(par7);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        float f = ageInTicks - (float)entityIn.ticksExisted;
        float f1 = ((EntityPolarBearTFC)entityIn).getStandingAnimationScale(f);
        f1 *= f1;
        float f2 = 1.0F - f1;
        this.body.rotateAngleX = 1.5707964F - f1 * 3.1415927F * 0.35F;
        this.body.rotationPointY = 9.0F * f2 + 11.0F * f1;
        this.leg3.rotationPointY = 14.0F * f2 + -6.0F * f1;
        this.leg3.rotationPointZ = -8.0F * f2 + -4.0F * f1;
        ModelRenderer var10000 = this.leg3;
        var10000.rotateAngleX -= f1 * 3.1415927F * 0.45F;
        this.leg4.rotationPointY = this.leg3.rotationPointY;
        this.leg4.rotationPointZ = this.leg3.rotationPointZ;
        var10000 = this.leg4;
        var10000.rotateAngleX -= f1 * 3.1415927F * 0.45F;
        this.head.rotationPointY = 10.0F * f2 + -12.0F * f1;
        this.head.rotationPointZ = -16.0F * f2 + -3.0F * f1;
        var10000 = this.head;
        var10000.rotateAngleX += f1 * 3.1415927F * 0.15F;

    }
}