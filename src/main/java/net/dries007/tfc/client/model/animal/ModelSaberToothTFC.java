/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.model.animal;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class ModelSaberToothTFC extends ModelBase
{
    public ModelRenderer frontBody;
    public ModelRenderer neckBase;
    public ModelRenderer neck;
    public ModelRenderer upperLeg3;
    public ModelRenderer upperLeg1;
    public ModelRenderer upperLeg2;
    public ModelRenderer upperLeg4;
    public ModelRenderer backBody;
    public ModelRenderer tailBody;
    public ModelRenderer tailTop;
    public ModelRenderer tailBottom;
    public ModelRenderer head;
    public ModelRenderer ear2;
    public ModelRenderer ear1;
    public ModelRenderer nose;
    public ModelRenderer tooth1;
    public ModelRenderer tooth2;
    public ModelRenderer babytooth1;
    public ModelRenderer babytooth2;
    public ModelRenderer mouthBottom;
    public ModelRenderer mouthTop;
    public ModelRenderer leg3;
    public ModelRenderer paw3;
    public ModelRenderer leg1;
    public ModelRenderer paw1;
    public ModelRenderer leg2;
    public ModelRenderer paw2;
    public ModelRenderer leg4;
    public ModelRenderer paw4;

    public ModelSaberToothTFC()
    {
        textureWidth = 64;
        textureHeight = 64;

        paw2 = new ModelRenderer(this, 21, 47);
        paw2.setRotationPoint(0.0F, 10.0F, -1.9F);
        paw2.addBox(-2.0F, -1.0F, -1.0F, 3, 2, 1, 0.0F);
        tailBottom = new ModelRenderer(this, 44, 11);
        tailBottom.setRotationPoint(-1.0F, 15.7F, 11.3F);
        tailBottom.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, -0.1F);
        setRotation(tailBottom, 0.9560913642424937F, 0.0F, 0.0F);
        leg3 = new ModelRenderer(this, 19, 50);
        leg3.mirror = true;
        leg3.setRotationPoint(0.5F, 0.9F, 0.5F);
        leg3.addBox(-2.0F, 0.0F, -2.0F, 3, 11, 3, 0.0F);
        upperLeg1 = new ModelRenderer(this, 0, 50);
        upperLeg1.setRotationPoint(5.0F, 11.5F, -5.0F);
        upperLeg1.addBox(-2.0F, -4.5F, -2.5F, 4, 9, 5, 0.0F);
        paw3 = new ModelRenderer(this, 21, 47);
        paw3.setRotationPoint(0.0F, 10.0F, -2.0F);
        paw3.addBox(-2.0F, -1.0F, -1.0F, 3, 2, 1, 0.0F);
        babytooth2 = new ModelRenderer(this, 9, 1);
        babytooth2.setRotationPoint(0.8F, 12.0F, -10.2F);
        babytooth2.addBox(-2.8F, -0.2F, -7.2F, 1, 2, 1, -0.1F);
        setRotation(babytooth2, 0.136659280431156F, 0.0F, 0.0F);
        ear1 = new ModelRenderer(this, 0, 4);
        ear1.mirror = true;
        ear1.setRotationPoint(2.0F, -5.8F, -0.9F);
        ear1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        setRotation(ear1, -0.7285004297824331F, 0.0F, 0.40980330836826856F);
        mouthTop = new ModelRenderer(this, 21, 19);
        mouthTop.setRotationPoint(-2.5F, -2.0F, -8.3F);
        mouthTop.addBox(0.0F, 0.0F, 0.5F, 4, 3, 4, 0.0F);
        paw4 = new ModelRenderer(this, 21, 47);
        paw4.setRotationPoint(0.5F, 10.0F, -2.0F);
        paw4.addBox(-2.0F, -1.0F, -1.0F, 3, 2, 1, 0.0F);
        upperLeg4 = new ModelRenderer(this, 1, 38);
        upperLeg4.setRotationPoint(-4.7F, 12.1F, 7.0F);
        upperLeg4.addBox(-2.0F, -4.0F, -2.0F, 4, 8, 4, 0.0F);
        upperLeg3 = new ModelRenderer(this, 1, 38);
        upperLeg3.setRotationPoint(4.7F, 12.1F, 7.0F);
        upperLeg3.addBox(-2.0F, -4.0F, -2.0F, 4, 8, 4, 0.0F);
        neck = new ModelRenderer(this, 2, 16);
        neck.setRotationPoint(0.0F, 10.6F, -5.0F);
        neck.addBox(-2.0F, -2.5F, -6.5F, 4, 5, 5, 0.0F);
        setRotation(neck, 0.091106186954104F, 0.0F, 0.0F);
        paw1 = new ModelRenderer(this, 21, 47);
        paw1.setRotationPoint(0.0F, 10.0F, -2.0F);
        paw1.addBox(-2.0F, -1.0F, -1.0F, 3, 2, 1, 0.0F);
        leg1 = new ModelRenderer(this, 19, 50);
        leg1.mirror = true;
        leg1.setRotationPoint(0.4F, 1.5F, 0.5F);
        leg1.addBox(-2.0F, 0.0F, -2.0F, 3, 11, 3, 0.0F);
        leg2 = new ModelRenderer(this, 19, 50);
        leg2.setRotationPoint(0.6F, 1.5F, 0.5F);
        leg2.addBox(-2.0F, 0.0F, -2.0F, 3, 11, 3, 0.0F);
        upperLeg2 = new ModelRenderer(this, 0, 50);
        upperLeg2.setRotationPoint(-5.0F, 11.5F, -5.0F);
        upperLeg2.addBox(-2.0F, -4.5F, -2.5F, 4, 9, 5, 0.0F);
        tooth1 = new ModelRenderer(this, 16, 0);
        tooth1.setRotationPoint(0.8F, 12.0F, -10.2F);
        tooth1.addBox(0.2F, -0.2F, -7.2F, 1, 4, 1, -0.1F);
        setRotation(tooth1, 0.136659280431156F, 0.0F, 0.0F);
        frontBody = new ModelRenderer(this, 32, 46);
        frontBody.setRotationPoint(0.5F, 12.3F, -7.8F);
        frontBody.addBox(-4.0F, -5.0F, 0.0F, 7, 9, 9, 0.0F);
        setRotation(frontBody, -0.045553093477052F, 0.0F, 0.0F);
        tailBody = new ModelRenderer(this, 42, 22);
        tailBody.setRotationPoint(0.0F, 11.2F, 9.7F);
        tailBody.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, -0.2F);
        setRotation(tailBody, 0.5009094953223726F, 0.0F, 0.0F);
        neckBase = new ModelRenderer(this, 0, 26);
        neckBase.setRotationPoint(0.5F, 11.6F, -5.4F);
        neckBase.addBox(-4.0F, -4.0F, -3.0F, 7, 7, 4, -0.1F);
        setRotation(neckBase, 0.136659280431156F, 0.0F, 0.0F);
        ear2 = new ModelRenderer(this, 0, 4);
        ear2.setRotationPoint(-3.9F, -5.4F, -0.9F);
        ear2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        setRotation(ear2, -0.7285004297824331F, 0.0F, -0.40980330836826856F);
        mouthBottom = new ModelRenderer(this, 23, 14);
        mouthBottom.setRotationPoint(-2.0F, -0.1F, -8.0F);
        mouthBottom.addBox(0.0F, 0.0F, 0.5F, 3, 2, 3, 0.0F);
        nose = new ModelRenderer(this, 16, 3);
        nose.setRotationPoint(-1.5F, -2.3F, -8.6F);
        nose.addBox(0.0F, 0.0F, 0.4F, 2, 2, 4, 0.0F);
        setRotation(nose, 0.18203784098300857F, 0.0F, 0.0F);
        backBody = new ModelRenderer(this, 32, 28);
        backBody.setRotationPoint(0.0F, 12.3F, 4.2F);
        backBody.addBox(-3.0F, -4.0F, -4.0F, 6, 8, 10, 0.0F);
        tailTop = new ModelRenderer(this, 44, 16);
        tailTop.setRotationPoint(-1.0F, 12.4F, 9.1F);
        tailTop.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        setRotation(tailTop, 0.5918411493512771F, 0.0F, 0.0F);
        babytooth1 = new ModelRenderer(this, 9, 1);
        babytooth1.setRotationPoint(0.8F, 12.0F, -10.2F);
        babytooth1.addBox(0.2F, -0.2F, -7.2F, 1, 2, 1, -0.1F);
        setRotation(babytooth1, 0.136659280431156F, 0.0F, 0.0F);
        head = new ModelRenderer(this, 1, 4);
        head.setRotationPoint(0.5F, 12.0F, -10.0F);
        head.addBox(-3.0F, -4.0F, -4.5F, 5, 6, 5, 0.1F);
        leg4 = new ModelRenderer(this, 19, 50);
        leg4.setRotationPoint(0.0F, 0.9F, 0.5F);
        leg4.addBox(-1.5F, 0.0F, -2.0F, 3, 11, 3, 0.0F);
        tooth2 = new ModelRenderer(this, 16, 0);
        tooth2.setRotationPoint(0.8F, 12.0F, -10.2F);
        tooth2.addBox(-2.8F, -0.2F, -7.2F, 1, 4, 1, -0.1F);
        setRotation(tooth2, 0.136659280431156F, 0.0F, 0.0F);

        leg2.addChild(paw2);
        upperLeg3.addChild(leg3);
        leg3.addChild(paw3);
        head.addChild(ear1);
        head.addChild(mouthTop);
        leg4.addChild(paw4);
        leg1.addChild(paw1);
        upperLeg1.addChild(leg1);
        upperLeg2.addChild(leg2);
        head.addChild(ear2);
        head.addChild(mouthBottom);
        head.addChild(nose);
        upperLeg4.addChild(leg4);
    }


    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        float age = 1;

        if (isChild)
        {
            float aa = 2F - (1.0F - age);
            GlStateManager.pushMatrix();
            float ab = (float) Math.sqrt(1.0F / aa);
            GlStateManager.scale(ab, ab, ab);
            GlStateManager.translate(0.0F, 24F * f5 * age / aa, 2F * f5 * age / ab);


            head.render(f5);
            tooth1.isHidden = true;
            tooth2.isHidden = true;
            babytooth1.isHidden = false;
            babytooth2.isHidden = false;
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F / aa, 1.0F / aa, 1.0F / aa);
            GlStateManager.translate(0.0F, 24F * f5 * age, 0.0F);

            neck.render(f5);
            neckBase.render(f5);
            frontBody.render(f5);
            backBody.render(f5);
            tailBody.render(f5);
            tailTop.render(f5);
            tailBottom.render(f5);
            upperLeg1.render(f5);
            upperLeg2.render(f5);
            upperLeg3.render(f5);
            upperLeg4.render(f5);
            GlStateManager.popMatrix();

        }
        else
        {
            head.render(f5);

            babytooth1.isHidden = true;
            babytooth2.isHidden = true;
            GlStateManager.pushMatrix();
            //GlStateManager.translate(this.neck.offsetX, this.neck.offsetY, this.neck.offsetZ);
            //GlStateManager.translate(this.neck.rotationPointX * f5, this.neck.rotationPointY * f5, this.neck.rotationPointZ * f5);
            GlStateManager.scale(0.9D, 0.9D, 1.0D);
            //GlStateManager.translate(-this.neck.offsetX, -this.neck.offsetY, -this.neck.offsetZ);
            //GlStateManager.translate(-this.neck.rotationPointX * f5, -this.neck.rotationPointY * f5, -this.neck.rotationPointZ * f5);

            neck.render(f5);
            neckBase.render(f5);
            frontBody.render(f5);
            backBody.render(f5);
            tailBody.render(f5);
            tailTop.render(f5);
            tailBottom.render(f5);
            upperLeg1.render(f5);
            upperLeg2.render(f5);
            upperLeg3.render(f5);
            upperLeg4.render(f5);
            GlStateManager.popMatrix();

        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        //super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.rotateAngleX = f4 / (180F / (float) Math.PI);
        head.rotateAngleY = f3 / (180F / (float) Math.PI);
        upperLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        upperLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F + 0.3F) * f1;
        upperLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F + 0.3F) * f1;
        upperLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}