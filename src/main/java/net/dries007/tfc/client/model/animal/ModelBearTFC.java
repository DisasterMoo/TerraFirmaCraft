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

import net.dries007.tfc.objects.entity.animal.EntityBearTFC;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class ModelBearTFC extends ModelBase
{
    private ModelRenderer bearHead;
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
    private ModelRenderer neck;


    public ModelBearTFC()
    {
        textureWidth = 80;
        textureHeight = 64;

        this.frontbody = new ModelRenderer(this, 29, 39);
        this.frontbody.setRotationPoint(-1.0F, 11F, 9F);
        this.frontbody.addBox(-5.0F, -19.0F, -6.5F, 13, 13, 12, 0.0F);
        this.setRotation(frontbody, 1.5707963267948966F, 0.0F, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 47);
        this.leg3.setRotationPoint(4.5F, 12.0F, 5.0F);
        this.leg3.addBox(-1.0F, 0.0F, -1.0F, 5, 12, 5, 0.0F);
        this.bearHead = new ModelRenderer(this, 0, 8);
        this.bearHead.setRotationPoint(0.0F, 9.0F, -4.0F);
        this.bearHead.addBox(-3.0F, -5.2F, -2.5F, 7, 7, 6, 0.0F);
        this.nose = new ModelRenderer(this, 5, 0);
        this.nose.setRotationPoint(0.3F, 0.0F, -0.2F);
        this.nose.addBox(-2.0F, -2.2F, -5.5F, 4, 4, 4, 0.0F);
        this.tail = new ModelRenderer(this, 48, 3);
        this.tail.setRotationPoint(-1.0F, -3.8F, -0.5F);
        this.tail.addBox(-1.0F, -1.0F, -1.0F, 3, 3, 3, 0.0F);
        this.setRotation(tail, -0.36425021489121656F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 47);
        this.leg4.setRotationPoint(-6.5F, 12.0F, 5.0F);
        this.leg4.addBox(-1.0F, 0.0F, -1.0F, 5, 12, 5, 0.0F);
        this.ear2 = new ModelRenderer(this, 23, 11);
        this.ear2.setRotationPoint(0.8F, -2.5F, 1.5F);
        this.ear2.addBox(-3.5F, -4.0F, 0.0F, 2, 2, 1, 0.0F);
        this.leg2 = new ModelRenderer(this, 1, 30);
        this.leg2.setRotationPoint(-6.5F, 0.0F, -8.0F);
        this.leg2.addBox(-1.0F, -2.0F, -1.0F, 4, 12, 5, 0.0F);
        this.leg1 = new ModelRenderer(this, 1, 30);
        this.leg1.setRotationPoint(5.5F, 0.0F, -8.0F);
        this.leg1.addBox(-1.0F, -2.0F, -1.0F, 4, 12, 5, 0.0F);
        this.ear1 = new ModelRenderer(this, 23, 11);
        //this.ear1.mirror = true;
        this.ear1.setRotationPoint(-1.8F, -2.5F, 1.5F);
        this.ear1.addBox(3.5F, -4.0F, 0.0F, 2, 2, 1, 0.0F);
        this.neck = new ModelRenderer(this, 42, 9);
        this.neck.setRotationPoint(-1F, -26F, -1.0F);
        this.neck.addBox(-0.5F, 3.0F, -1.4F, 6, 5, 6, -0.2F);
        this.rearbody = new ModelRenderer(this, 32, 20);
        this.rearbody.setRotationPoint(0.5F, 10.5F, 14.5F);
        this.rearbody.addBox(-6.0F, -13.0F, -6.0F, 12, 9, 10, 0.0F);
        this.setRotation(rearbody, 1.4114477660878142F, 0.0F, 0.0F);

        bearHead.addChild(nose);
        bearHead.addChild(ear2);
        rearbody.addChild(tail);
        bearHead.addChild(ear1);
        frontbody.addChild(neck);

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

        EntityBearTFC bear = ((EntityBearTFC) entity);

        float percent = (float) bear.getPercentToAdulthood();

        float ageScale = 2.0F - percent;
        float ageHeadScale = (float) Math.pow(1 / ageScale, 0.66);
        GlStateManager.pushMatrix();

        GlStateManager.translate(0.0F, 0.75f - (0.75f * percent), 0f);
        GlStateManager.scale(ageHeadScale, ageHeadScale, ageHeadScale);
        GlStateManager.translate(0.0F, 0, 0.1875f - (0.1875f * percent));

        bearHead.render(par7);
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
        bearHead.rotateAngleX = headPitch / (180F / (float) Math.PI);
        bearHead.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

        leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;


        float f = ageInTicks - (float)entityIn.ticksExisted;
        float f1 = ((EntityBearTFC)entityIn).getStandingAnimationScale(f);
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
        this.bearHead.rotationPointY = 10.0F * f2 + -12.0F * f1;
        this.bearHead.rotationPointZ = -16.0F * f2 + -3.0F * f1;
        var10000 = this.bearHead;
        var10000.rotateAngleX += f1 * 3.1415927F * 0.15F;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}