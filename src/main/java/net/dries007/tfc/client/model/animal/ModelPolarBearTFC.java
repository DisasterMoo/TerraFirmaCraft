/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.model.animal;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.objects.entity.animal.EntityPolarBearTFC;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class ModelPolarBearTFC extends ModelBase
{
    private ModelRenderer head;
    private ModelRenderer rearbody;
    private ModelRenderer leg2;
    private ModelRenderer leg4;
    private ModelRenderer leg1;
    private ModelRenderer leg3;
    private ModelRenderer frontbody;
    private ModelRenderer ear2;
    private ModelRenderer ear1;
    private ModelRenderer nose;
    private ModelRenderer tail;


    public ModelPolarBearTFC()
    {
        textureWidth = 80;
        textureHeight = 64;

        ear1 = new ModelRenderer(this, 26, 12);
        ear1.mirror = true;
        ear1.setRotationPoint(0.2F, -0.5F, 0.0F);
        ear1.addBox(2.5F, -4.0F, 0.0F, 2, 2, 1, 0.0F);
        ear2 = new ModelRenderer(this, 26, 12);
        ear2.setRotationPoint(-0.1F, -0.5F, 0.0F);
        ear2.addBox(-4.5F, -4.0F, 0.0F, 2, 2, 1, 0.0F);
        head = new ModelRenderer(this, 0, 7);
        head.setRotationPoint(0.0F, 7.8F, -16.0F);
        head.addBox(-4.0F, -3.0F, -3.5F, 8, 7, 8, 0.0F);
        nose = new ModelRenderer(this, 7, 0);
        nose.setRotationPoint(0.0F, 0.0F, -0.2F);
        nose.addBox(-3.0F, 0.0F, -6.0F, 6, 4, 3, 0.0F);
        frontbody = new ModelRenderer(this, 25, 40);
        frontbody.setRotationPoint(-2.0F, 8.6F, 12.0F);
        frontbody.addBox(-5.0F, -25.0F, -6.0F, 14, 12, 12, 0.0F);
        setRotation(frontbody, 1.5707963267948966F, 0.0F, 0.0F);
        rearbody = new ModelRenderer(this, 22, 13);
        rearbody.setRotationPoint(-2.0F, 9.0F, 12.0F);
        rearbody.addBox(-6.0F, -13.0F, -6.0F, 16, 14, 13, 0.0F);
        setRotation(rearbody, 1.5707963705062866F, 0.0F, 0.0F);
        tail = new ModelRenderer(this, 45, 7);
        tail.setRotationPoint(1.5F, 0.7F, 0.0F);
        tail.addBox(-1.0F, -1.0F, -1.0F, 3, 3, 3, 0.0F);
        setRotation(tail, -0.36425021489121656F, 0.0F, 0.0F);
        leg1 = new ModelRenderer(this, 2, 28);
        leg1.setRotationPoint(4.0F, 13.0F, -8.0F);
        leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 6, 0.0F);
        leg2 = new ModelRenderer(this, 2, 28);
        leg2.setRotationPoint(-4.0F, 13.0F, -8.0F);
        leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 6, 0.0F);
        leg3 = new ModelRenderer(this, 0, 45);
        leg3.setRotationPoint(4.5F, 13.0F, 6.0F);
        leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 8, 0.0F);
        leg4 = new ModelRenderer(this, 0, 45);
        leg4.setRotationPoint(-4.5F, 13.0F, 6.0F);
        leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 8, 0.0F);

        head.addChild(ear1);
        head.addChild(ear2);
        rearbody.addChild(tail);
        head.addChild(nose);

        --this.leg3.rotationPointX;
        ++this.leg4.rotationPointX;
        ModelRenderer var10000 = this.leg3;
        var10000.rotationPointZ += 0.0F;
        var10000 = this.leg4;
        var10000.rotationPointZ += 0.0F;
        --this.leg1.rotationPointX;
        ++this.leg2.rotationPointX;
        --this.leg1.rotationPointZ;
        --this.leg2.rotationPointZ;
        //this.childZOffset += 2.0F;
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

        frontbody.render(par7);
        rearbody.render(par7);
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
        head.rotateAngleX = headPitch / (180F / (float) Math.PI);
        head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

        leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;


        float f = ageInTicks - (float) entityIn.ticksExisted;
        float f1 = ((EntityPolarBearTFC) entityIn).getStandingAnimationScale(f);
        f1 *= f1;
        float f2 = 1.0F - f1;
        this.frontbody.rotateAngleX = 1.5707964F - f1 * 3.1415927F * 0.35F;
        this.frontbody.rotationPointY = 9.0F * f2 + 11.0F * f1;
        this.leg1.rotationPointY = 14.0F * f2 + -6.0F * f1;
        this.leg1.rotationPointZ = -8.0F * f2 + -4.0F * f1;
        ModelRenderer var10000 = this.leg1;
        var10000.rotateAngleX -= f1 * 3.1415927F * 0.45F;
        this.leg2.rotationPointY = this.leg1.rotationPointY;
        this.leg2.rotationPointZ = this.leg1.rotationPointZ;
        var10000 = this.leg2;
        var10000.rotateAngleX -= f1 * 3.1415927F * 0.45F;
        this.head.rotationPointY = 10.0F * f2 + -12.0F * f1;
        this.head.rotationPointZ = -16.0F * f2 + -3.0F * f1;
        var10000 = this.head;
        var10000.rotateAngleX += f1 * 3.1415927F * 0.15F;

    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}