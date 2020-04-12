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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.api.types.IAnimalTFC;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class ModelCamelTFC extends ModelBase
{
    public ModelRenderer saddle;
    public ModelRenderer frontLegLeftTop;
    public ModelRenderer humpBottom;
    public ModelRenderer frontLegRightTop;
    public ModelRenderer humpMiddle;
    public ModelRenderer neckBase;
    public ModelRenderer bodyBack;
    public ModelRenderer tail;
    public ModelRenderer backLegLeftTop;
    public ModelRenderer bodyFront;
    public ModelRenderer backLegRightTop;
    public ModelRenderer saddlePostBack;
    public ModelRenderer saddlePostFront;
    public ModelRenderer bridleFront1;
    public ModelRenderer bridleBack1;
    public ModelRenderer bridleLeft1;
    public ModelRenderer bridleRight1;
    public ModelRenderer bridleFront2;
    public ModelRenderer bridleBack2;
    public ModelRenderer bridleLeft2;
    public ModelRenderer bridleRight2;
    public ModelRenderer bridleFrontTop1;
    public ModelRenderer bridleFrontBottom1;
    public ModelRenderer bridleFrontLeft1;
    public ModelRenderer bridleFrontRight1;
    public ModelRenderer bridleFrontTop2;
    public ModelRenderer bridleFrontBottom2;
    public ModelRenderer bridleFrontLeft2;
    public ModelRenderer bridleFrontRight2;
    public ModelRenderer strapChestLeft;
    public ModelRenderer strapBellyRight;
    public ModelRenderer strapBellyLeft;
    public ModelRenderer strapBellyBottom;
    public ModelRenderer strapFrontLeft;
    public ModelRenderer strapChestRight;
    public ModelRenderer strapChestBottom;
    public ModelRenderer frontLegLeftBottom;
    public ModelRenderer toesFrontLeft;
    public ModelRenderer frontLegRightBottom;
    public ModelRenderer toesFrontRight;
    public ModelRenderer neckUpper;
    public ModelRenderer head;
    public ModelRenderer earRight;
    public ModelRenderer snout;
    public ModelRenderer earLeft;
    public ModelRenderer mandible;
    public ModelRenderer backLegLeftMiddle;
    public ModelRenderer backLegLeftBottom;
    public ModelRenderer toesBackLeft;
    public ModelRenderer backLegRightMiddle;
    public ModelRenderer backLegRightBottom;
    public ModelRenderer toesBackRight;
    public ModelRenderer chestLeft;
    public ModelRenderer chestRight;
    public ModelRenderer frontLegLeftMiddle;
    public ModelRenderer frontLegRightMiddle;

    public ModelCamelTFC(float scale) {

        textureWidth = 128;
        textureHeight = 80;

        strapChestLeft = new ModelRenderer(this, 0, 0);
        strapChestLeft.setRotationPoint(5.01F, 8.1F, -6.0F);
        strapChestLeft.addBox(0.0F, 0.0F, -0.5F, 0, 12, 1, 0.0F);
        strapBellyBottom = new ModelRenderer(this, 0, 0);
        strapBellyBottom.setRotationPoint(5.2F, 18.1F, 6.7F);
        strapBellyBottom.addBox(-9.2F, 0.0F, -0.5F, 8, 0, 1, 0.0F);
        strapBellyRight = new ModelRenderer(this, 0, 0);
        strapBellyRight.setRotationPoint(-4.01F, 8.1F, 6.7F);
        strapBellyRight.addBox(0.0F, 0.0F, -0.5F, 0, 10, 1, 0.0F);
        strapChestBottom = new ModelRenderer(this, 0, 0);
        strapChestBottom.setRotationPoint(4.2F, 20.1F, -6.0F);
        strapChestBottom.addBox(-9.2F, 0.0F, -0.5F, 10, 0, 1, 0.0F);
        strapChestRight = new ModelRenderer(this, 0, 0);
        strapChestRight.setRotationPoint(-5.01F, 8.1F, -6.0F);
        strapChestRight.addBox(0.0F, 0.0F, -0.5F, 0, 12, 1, 0.0F);
        strapBellyLeft = new ModelRenderer(this, 0, 0);
        strapBellyLeft.setRotationPoint(4.01F, 8.1F, 6.7F);
        strapBellyLeft.addBox(0.0F, 0.0F, -0.5F, 0, 10, 1, 0.0F);

        toesBackLeft = new ModelRenderer(this, 110, 64);
        toesBackLeft.setRotationPoint(0.0F, 7.8F, 0.0F);
        toesBackLeft.addBox(-1.5F, 0.0F, -2.0F, 3, 2, 3, 0.0F);
        setRotation(toesBackLeft, 0.17453292012214658F, 0.0F, 0.0F);
        toesFrontRight = new ModelRenderer(this, 110, 64);
        toesFrontRight.setRotationPoint(0.0F, 8.0F, 0.0F);
        toesFrontRight.addBox(-1.5F, 0.0F, -2.0F, 3, 2, 3, 0.0F);

        tail = new ModelRenderer(this, 26, 0);
        tail.setRotationPoint(-0.5F, 1.5F, 8.0F);
        tail.addBox(-0.5F, 0.0F, -0.5F, 1, 12, 1, 0.0F);
        setRotation(tail, 0.17976891295541594F, 0.0F, 0.0F);

        mandible = new ModelRenderer(this, 49, 9);
        mandible.setRotationPoint(0.0F, 2.0F, -2.2F);
        mandible.addBox(-1.0F, 0.0F, -3.0F, 2, 1, 3, 0.0F);
        setRotation(mandible, 0.17453292519943295F, 0.0F, 0.0F);

        neckUpper = new ModelRenderer(this, 2, 33);
        neckUpper.setRotationPoint(0.0F, 0.0F, -5.4F);
        neckUpper.addBox(-1.5F, -12.0F, -3.0F, 3, 13, 4, 0.0F);
        setRotation(neckUpper, 0.2617993950843811F, 0.0F, 0.0F);
        earLeft = new ModelRenderer(this, 51, 5);
        earLeft.setRotationPoint(1.6F, -3.0F, 1.0F);
        earLeft.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
        setRotation(earLeft, 0.0F, -0.3490658503988659F, 0.5235987755982988F);

        earRight = new ModelRenderer(this, 51, 5);
        earRight.mirror = true;
        earRight.setRotationPoint(-1.6F, -3.0F, 1.0F);
        earRight.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
        setRotation(earRight, 0.0F, 0.3490658503988659F, -0.5235987755982988F);
        snout = new ModelRenderer(this, 46, 14);
        snout.setRotationPoint(0.0F, -2.5F, -3.0F);
        snout.addBox(-1.5F, 0.0F, -5.0F, 3, 3, 5, 0.0F);

        toesFrontLeft = new ModelRenderer(this, 110, 64);
        toesFrontLeft.setRotationPoint(0.0F, 8.0F, 0.0F);
        toesFrontLeft.addBox(-1.5F, 0.0F, -2.0F, 3, 2, 3, 0.0F);

        toesBackRight = new ModelRenderer(this, 110, 64);
        toesBackRight.setRotationPoint(0.0F, 7.8F, 0.0F);
        toesBackRight.addBox(-1.5F, 0.0F, -2.0F, 3, 2, 3, 0.0F);
        setRotation(toesBackRight, 0.17453292012214658F, 0.0F, 0.0F);
        neckBase = new ModelRenderer(this, 40, 34);
        neckBase.setRotationPoint(0.0F, 1.5F, -12.0F);
        neckBase.addBox(-2.5F, -3.5F, -8.0F, 5, 6, 9, 0.0F);
        setRotation(neckBase, -0.10471975803375246F, 0.0F, 0.0F);
        head = new ModelRenderer(this, 45, 23);
        head.setRotationPoint(0.0F, -12.0F, -0.3F);
        head.addBox(-2.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);

        this.frontLegLeftTop = new ModelRenderer(this, 110, 38);
        this.frontLegLeftTop.setRotationPoint(4.8F, -1.0F, -8.5F);
        this.frontLegLeftTop.addBox(-1.5F, -1.0F, -1.5F, 3, 7, 3, 0.2F);
        this.setRotation(frontLegLeftTop, 0.13962634015954636F, 0.0F, 0.0F);
        this.backLegLeftTop = new ModelRenderer(this, 109, 49);
        this.backLegLeftTop.setRotationPoint(4.5F, -2.5F, 10.0F);
        this.backLegLeftTop.addBox(-1.5F, 0.0F, -2.0F, 3, 10, 4, 0.05F);
        this.setRotation(backLegLeftTop, -0.10471975511965977F, 0.0F, 0.0F);
        this.frontLegRightTop = new ModelRenderer(this, 110, 38);
        this.frontLegRightTop.mirror = true;
        this.frontLegRightTop.setRotationPoint(-4.8F, -1.0F, -8.5F);
        this.frontLegRightTop.addBox(-1.5F, -1.0F, -1.5F, 3, 7, 3, 0.2F);
        this.setRotation(frontLegRightTop, 0.13962634015954636F, 0.0F, 0.0F);
        this.backLegRightTop = new ModelRenderer(this, 109, 49);
        this.backLegRightTop.setRotationPoint(-4.5F, -2.5F, 10.0F);
        this.backLegRightTop.addBox(-1.5F, 0.0F, -2.0F, 3, 10, 4, 0.05F);
        this.setRotation(backLegRightTop, -0.10471975511965977F, 0.0F, 0.0F);
        this.frontLegLeftMiddle = new ModelRenderer(this, 110, 13);
        this.frontLegLeftMiddle.setRotationPoint(-1.5F, 5.5F, -1.3F);
        this.frontLegLeftMiddle.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.setRotation(frontLegLeftMiddle, -0.19198621771937624F, 0.0F, 0.0F);
        this.frontLegRightMiddle = new ModelRenderer(this, 110, 13);
        this.frontLegRightMiddle.mirror = true;
        this.frontLegRightMiddle.setRotationPoint(-1.5F, 5.5F, -1.3F);
        this.frontLegRightMiddle.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.setRotation(frontLegRightMiddle, -0.19198621771937624F, 0.0F, 0.0F);
        this.backLegLeftMiddle = new ModelRenderer(this, 110, 26);
        this.backLegLeftMiddle.setRotationPoint(0.0F, 9.5F, -0.2F);
        this.backLegLeftMiddle.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotation(backLegLeftMiddle, 0.33161255787892263F, 0.0F, 0.0F);
        this.backLegRightMiddle = new ModelRenderer(this, 110, 26);
        this.backLegRightMiddle.setRotationPoint(0.0F, 9.5F, -0.2F);
        this.backLegRightMiddle.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotation(backLegRightMiddle, 0.33161255787892263F, 0.0F, 0.0F);
        this.frontLegRightBottom = new ModelRenderer(this, 112, 1);
        this.frontLegRightBottom.mirror = true;
        this.frontLegRightBottom.setRotationPoint(1.5F, 8.5F, 1.5F);
        this.frontLegRightBottom.addBox(-1.0F, 0.0F, -1.1F, 2, 9, 2, 0.0F);
        this.setRotation(frontLegRightBottom, 0.05235987755982988F, 0.0F, 0.0F);
        this.frontLegLeftBottom = new ModelRenderer(this, 112, 1);
        this.frontLegLeftBottom.setRotationPoint(1.5F, 8.5F, 1.5F);
        this.frontLegLeftBottom.addBox(-1.0F, 0.0F, -1.1F, 2, 9, 2, 0.0F);
        this.setRotation(frontLegLeftBottom, 0.05235987755982988F, 0.0F, 0.0F);
        this.backLegLeftBottom = new ModelRenderer(this, 112, 1);
        this.backLegLeftBottom.setRotationPoint(0.0F, 7.5F, 0.0F);
        this.backLegLeftBottom.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotation(backLegLeftBottom, -0.22689280275926282F, 0.0F, 0.0F);
        this.backLegRightBottom = new ModelRenderer(this, 112, 1);
        this.backLegRightBottom.setRotationPoint(0.0F, 7.5F, 0.0F);
        this.backLegRightBottom.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotation(backLegRightBottom, -0.22689280275926282F, 0.0F, 0.0F);






        chestRight = new ModelRenderer(this, 76, 0);
        chestRight.mirror = true;
        chestRight.setRotationPoint(-8.0F, -4.5F, 3.5F);
        chestRight.addBox(-0.2F, 0.0F, -4.0F, 3, 8, 8, 0.0F);
        setRotation(chestRight, 0.0F, 0.0F, -0.03490658503988659F);
        chestLeft = new ModelRenderer(this, 76, 0);
        chestLeft.setRotationPoint(5.0F, -4.5F, 3.5F);
        chestLeft.addBox(0.3F, 0.0F, -4.0F, 3, 8, 8, 0.0F);
        setRotation(chestLeft, 0.0F, 0.0F, 0.03490658503988659F);

        saddle = new ModelRenderer(this, 65, 17);
        saddle.setRotationPoint(0.0F, -10.0F, 0.0F);
        saddle.addBox(-3.5F, 1.0F, -7.2F, 7, 3, 15, 0.0F);
        saddlePostBack = new ModelRenderer(this, 0, 0);
        saddlePostBack.setRotationPoint(0.0F, 0.0F, 6.5F);
        saddlePostBack.addBox(-2.0F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        saddlePostFront = new ModelRenderer(this, 0, 0);
        saddlePostFront.setRotationPoint(0.0F, 0.0F, -6.0F);
        saddlePostFront.addBox(-0.5F, -2.0F, -0.4F, 1, 3, 1, 0.0F);

        bridleFront1 = new ModelRenderer(this, 0, 0);
        bridleFront1.setRotationPoint(0.0F, 0.0F, -3.1F);
        bridleFront1.addBox(-2.1F, -0.5F, 0.0F, 4, 1, 0, 0.0F);
        bridleBack1 = new ModelRenderer(this, 0, 0);
        bridleBack1.setRotationPoint(0.2F, 0.0F, 2.01F);
        bridleBack1.addBox(-2.1F, -0.5F, 0.0F, 4, 1, 0, 0.0F);
        bridleLeft1 = new ModelRenderer(this, 0, 0);
        bridleLeft1.setRotationPoint(2.1F, 0.0F, -0.38F);
        bridleLeft1.addBox(0.0F, -0.5F, -2.6F, 0, 1, 5, 0.0F);
        bridleRight1 = new ModelRenderer(this, 0, 0);
        bridleRight1.setRotationPoint(-2.11F, 0.0F, -0.35F);
        bridleRight1.addBox(0.0F, -0.5F, -2.6F, 0, 1, 5, 0.0F);
        bridleFront2 = new ModelRenderer(this, 0, 0);
        bridleFront2.setRotationPoint(0.2F, 0.0F, -3.1F);
        bridleFront2.addBox(-2.1F, -0.5F, 0.0F, 4, 1, 0, 0.0F);
        bridleBack2 = new ModelRenderer(this, 0, 0);
        bridleBack2.setRotationPoint(0.0F, 0.0F, 2.03F);
        bridleBack2.addBox(-2.1F, -0.5F, 0.0F, 4, 1, 0, 0.0F);
        bridleLeft2 = new ModelRenderer(this, 0, 0);
        bridleLeft2.setRotationPoint(2.09F, 0.0F, -0.5F);
        bridleLeft2.addBox(0.0F, -0.5F, -2.6F, 0, 1, 5, 0.0F);
        bridleRight2 = new ModelRenderer(this, 0, 0);
        bridleRight2.setRotationPoint(-2.1F, 0.0F, -0.5F);
        bridleRight2.addBox(0.0F, -0.5F, -2.6F, 0, 1, 5, 0.0F);
        bridleFrontTop1 = new ModelRenderer(this, 0, 0);
        bridleFrontTop1.setRotationPoint(0.0F, -2.6F, -3.5F);
        bridleFrontTop1.addBox(-1.6F, 0.0F, -0.5F, 3, 0, 1, 0.0F);
        bridleFrontBottom1 = new ModelRenderer(this, 0, 0);
        bridleFrontBottom1.setRotationPoint(0.0F, 0.6F, -3.5F);
        bridleFrontBottom1.addBox(-1.6F, 0.0F, -0.5F, 3, 0, 1, 0.0F);
        bridleFrontLeft1 = new ModelRenderer(this, 0, 0);
        bridleFrontLeft1.setRotationPoint(0.0F, -2.4F, -3.5F);
        bridleFrontLeft1.addBox(1.6F, 0.0F, -0.5F, 0, 3, 1, 0.0F);
        bridleFrontRight1 = new ModelRenderer(this, 0, 0);
        bridleFrontRight1.setRotationPoint(0.0F, -2.4F, -3.5F);
        bridleFrontRight1.addBox(-1.6F, 0.0F, -0.5F, 0, 3, 1, 0.0F);
        bridleFrontTop2 = new ModelRenderer(this, 0, 0);
        bridleFrontTop2.setRotationPoint(0.2F, -2.6F, -3.5F);
        bridleFrontTop2.addBox(-1.6F, 0.0F, -0.5F, 3, 0, 1, 0.0F);
        bridleFrontBottom2 = new ModelRenderer(this, 0, 0);
        bridleFrontBottom2.setRotationPoint(0.2F, 0.6F, -3.5F);
        bridleFrontBottom2.addBox(-1.6F, 0.0F, -0.5F, 3, 0, 1, 0.0F);
        bridleFrontLeft2 = new ModelRenderer(this, 0, 0);
        bridleFrontLeft2.setRotationPoint(0.01F, -2.4F, -3.5F);
        bridleFrontLeft2.addBox(1.6F, -0.2F, -0.5F, 0, 3, 1, 0.0F);
        bridleFrontRight2 = new ModelRenderer(this, 0, 0);
        bridleFrontRight2.setRotationPoint(0.0F, -2.6F, -3.5F);
        bridleFrontRight2.addBox(-1.6F, 0.0F, -0.5F, 0, 3, 1, 0.0F);

        bodyFront = new ModelRenderer(this, 1, 34);
        bodyFront.setRotationPoint(-5.0F, 3.0F, -12.0F);
        bodyFront.addBox(0.0F, -7.0F, 0.0F, 10, 13, 17, scale);
        bodyBack = new ModelRenderer(this, 11, 13);
        bodyBack.setRotationPoint(0.5F, -5.0F, 4.5F);
        bodyBack.addBox(-4.5F, 1.05F, -0.5F, 8, 11, 9, scale);
        humpMiddle = new ModelRenderer(this, 69, 36);
        humpMiddle.setRotationPoint(0.0F, -6.0F, 1.0F);
        humpMiddle.addBox(-2.5F, -1.95F, -6.0F, 5, 2, 11, scale);
        humpBottom = new ModelRenderer(this, 59, 50);
        humpBottom.setRotationPoint(0.0F, -5.0F, 0.0F);
        humpBottom.addBox(-3.5F, -0.95F, -9.0F, 7, 2, 18, scale);

        saddle.addChild(strapBellyBottom);
        saddle.addChild(strapBellyRight);
        backLegLeftBottom.addChild(toesBackLeft);
        frontLegRightBottom.addChild(toesFrontRight);
        bodyBack.addChild(tail);
        backLegRightTop.addChild(backLegRightMiddle);
        snout.addChild(mandible);
        backLegLeftMiddle.addChild(backLegLeftBottom);
        saddle.addChild(saddlePostFront);
        saddle.addChild(strapChestLeft);
        backLegLeftTop.addChild(backLegLeftMiddle);
        backLegRightMiddle.addChild(backLegRightBottom);
        neckBase.addChild(neckUpper);
        head.addChild(earLeft);
        //frontLegLeftTop.addChild(frontLegLeftBottom);
        saddle.addChild(strapChestBottom);
        saddle.addChild(strapChestRight);
        saddle.addChild(strapBellyLeft);
        saddle.addChild(saddlePostBack);
        head.addChild(earRight);
        head.addChild(snout);
        frontLegLeftBottom.addChild(toesFrontLeft);
        //frontLegRightTop.addChild(frontLegRightBottom);
        backLegRightBottom.addChild(toesBackRight);
        neckUpper.addChild(head);
        head.addChild(this.bridleLeft2);
        head.addChild(this.bridleFront2);
        head.addChild(this.bridleBack2);
        head.addChild(this.bridleFront1);
        head.addChild(this.bridleFrontLeft1);
        head.addChild(this.bridleBack1);
        head.addChild(this.bridleRight1);
        head.addChild(this.bridleFrontBottom2);
        head.addChild(this.bridleRight2);
        head.addChild(this.bridleFrontRight1);
        head.addChild(this.bridleFrontBottom1);
        head.addChild(this.bridleLeft1);
        head.addChild(this.bridleFrontTop1);
        head.addChild(this.bridleFrontTop2);
        head.addChild(this.bridleFrontLeft2);
        head.addChild(this.bridleFrontRight2);


        frontLegLeftTop.addChild(this.frontLegLeftMiddle);
        frontLegRightMiddle.addChild(this.frontLegRightBottom);
        frontLegLeftMiddle.addChild(this.frontLegLeftBottom);
        frontLegRightTop.addChild(this.frontLegRightMiddle);
    }


    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        AbstractChestHorse abstractchesthorse = (AbstractChestHorse) entityIn;
        boolean flag1 = !abstractchesthorse.isChild() && abstractchesthorse.hasChest();
        boolean flag2 = !isChild && abstractchesthorse.isHorseSaddled();
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);


        if (((EntityAnimal) entityIn).isChild())
        {
            double ageScale = 1;
            double percent = 1;
            if (entityIn instanceof IAnimalTFC)
            {
                percent = ((IAnimalTFC) entityIn).getPercentToAdulthood();
                ageScale = 1 / (2.0D - percent);
            }
            GlStateManager.scale(ageScale, ageScale, ageScale);
            GlStateManager.translate(0.0F, 1.5f - (1.5f * percent), 0f);
        }

        neckBase.render(scale);
        frontLegLeftTop.render(scale);
        frontLegRightTop.render(scale);
        backLegLeftTop.render(scale);
        backLegRightTop.render(scale);
        bodyBack.render(scale);
        bodyFront.render(scale);
        humpBottom.render(scale);
        humpMiddle.render(scale);

        bridleFront1.isHidden = true;
        bridleFront2.isHidden = true;
        bridleBack1.isHidden = true;
        bridleBack2.isHidden = true;
        bridleLeft1.isHidden = true;
        bridleLeft2.isHidden = true;
        bridleRight1.isHidden = true;
        bridleRight2.isHidden = true;


        if (flag1)
        {
            chestLeft.render(scale);
            chestRight.render(scale);
        }

        if (flag2)
        {
            saddle.render(scale);
        }

    }

    /*public void setRotationAngles(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

        //super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        //this.frontLegRightTop.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        //this.frontLegLeftTop.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        //this.backLegLeftTop.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        //this.backLegRightTop.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;


        float f9 = (float) entitylivingbaseIn.ticksExisted + ageInTicks;

        float f10 = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F);

        float f13 = MathHelper.cos(f9 * 0.6F + 3.1415927F);


        this.frontLegLeftTop.rotationPointY = -2.0F + 9.0F;
        this.frontLegLeftTop.rotationPointZ = -2.0F + -8.0F;
        this.frontLegRightTop.rotationPointY = this.frontLegLeftTop.rotationPointY;
        this.frontLegRightTop.rotationPointZ = this.frontLegLeftTop.rotationPointZ;
        this.backLegLeftMiddle.rotationPointY = this.backLegLeftTop.rotationPointY + MathHelper.sin(limbSwing * 1.5707964F) * 0.5F * limbSwingAmount * 7.0F;
        this.backLegLeftMiddle.rotationPointZ = this.backLegLeftTop.rotationPointZ + MathHelper.cos(limbSwing * -1.5707964F) * 0.5F * limbSwingAmount * 7.0F;
        this.backLegRightMiddle.rotationPointY = this.backLegRightTop.rotationPointY + MathHelper.sin(1.5707964F   * f10 * 0.5F * limbSwingAmount) * 7.0F;
        this.backLegRightMiddle.rotationPointZ = this.backLegRightTop.rotationPointZ + MathHelper.cos(-1.5707964F   * f10 * 0.5F * limbSwingAmount) * 7.0F;



        float f14 = (-1.0471976F + f13) ;
        float f15 = (-1.0471976F - f13) ;


        this.frontLegLeftBottom.rotationPointY = this.frontLegLeftTop.rotationPointY + MathHelper.sin(1.5707964F + f14) * 7.0F;
        this.frontLegLeftBottom.rotationPointZ = this.frontLegLeftTop.rotationPointZ + MathHelper.cos(-1.5707964F + f14) * 7.0F;
        this.frontLegRightBottom.rotationPointY = this.frontLegRightTop.rotationPointY + MathHelper.sin(1.5707964F + f15) * 7.0F;
        this.frontLegRightBottom.rotationPointZ = this.frontLegRightTop.rotationPointZ + MathHelper.cos(-1.5707964F + f15) * 7.0F;
        this.backLegLeftTop.rotateAngleX = -f10 * 0.5F * limbSwingAmount;
        this.backLegLeftMiddle.rotateAngleX = -0.08726646F  + (-f10 * 0.5F * limbSwingAmount - Math.max(0.0F, f10 * 0.5F * limbSwingAmount));
        this.toesBackLeft.rotateAngleX = this.backLegLeftMiddle.rotateAngleX;
        this.backLegRightMiddle.rotateAngleX = f10 * 0.5F * limbSwingAmount;
        this.backLegRightMiddle.rotateAngleX = -0.08726646F  + (f10 * 0.5F * limbSwingAmount - Math.max(0.0F, -f10 * 0.5F * limbSwingAmount));
        this.toesBackRight.rotateAngleX = this.backLegRightMiddle.rotateAngleX;
        this.frontLegLeftTop.rotateAngleX = f14;
        this.frontLegLeftBottom.rotateAngleX = (this.frontLegLeftTop.rotateAngleX + 3.1415927F * Math.max(0.0F, 0.2F + f13 * 0.2F))  + (Math.max(0.0F, f10 * 0.5F * limbSwingAmount));
        this.toesFrontLeft.rotateAngleX = this.frontLegLeftBottom.rotateAngleX;
        this.frontLegRightTop.rotateAngleX = f15;
        this.frontLegRightBottom.rotateAngleX = (this.frontLegRightTop.rotateAngleX + 3.1415927F * Math.max(0.0F, 0.2F - f13 * 0.2F))  + (Math.max(0.0F, -f10 * 0.5F * limbSwingAmount));
        this.toesFrontRight.rotateAngleX = this.frontLegRightBottom.rotateAngleX;
        this.toesBackLeft.rotationPointY = this.backLegLeftMiddle.rotationPointY;
        this.toesBackLeft.rotationPointZ = this.backLegLeftMiddle.rotationPointZ;
        this.toesBackRight.rotationPointY = this.backLegRightMiddle.rotationPointY;
        this.toesBackRight.rotationPointZ = this.backLegRightMiddle.rotationPointZ;
        this.toesFrontLeft.rotationPointY = this.frontLegLeftBottom.rotationPointY;
        this.toesFrontLeft.rotationPointZ = this.frontLegLeftBottom.rotationPointZ;
        this.toesFrontRight.rotationPointY = this.frontLegRightBottom.rotationPointY;
        this.toesFrontRight.rotationPointZ = this.frontLegRightBottom.rotationPointZ;

    }*/

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        head.rotateAngleX = f4 / (180F / (float) Math.PI);
        head.rotateAngleY = f3 / (180F / (float) Math.PI);

        frontLegLeftTop.rotateAngleX = MathHelper.cos(f / 1.5F + 3F * (float) Math.PI / 2F) * 0.7F * f1 + 0F;
        frontLegRightTop.rotateAngleX = MathHelper.cos(f / 1.5F + (float) Math.PI / 2F) * 0.7F * f1 + 0F;
        backLegRightTop.rotateAngleX = MathHelper.cos(f / 1.5F + (float) Math.PI * 7F / 4F) * 0.7F * f1 - 0F;
        backLegLeftTop.rotateAngleX = MathHelper.cos(f / 1.5F + 3f * (float) Math.PI / 4F) * 0.7F * f1 - 0F;
        frontLegRightBottom.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI / 2F) * 1.4F * f1;
        backLegRightMiddle.rotateAngleX = -MathHelper.sin(f / 1.5F + (float) Math.PI / 2F) * 0.7F * f1 - 0F;
        toesFrontRight.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI / 2F) * 2.1F * f1 + 0F;
        frontLegLeftBottom.rotateAngleX = MathHelper.sin(f / 1.5F + 3F * (float) Math.PI / 2F) * 1.4F * f1;
        backLegLeftMiddle.rotateAngleX = -MathHelper.sin(f / 1.5F + 3F * (float) Math.PI / 2F) * 0.7F * f1 - 0F;
        toesFrontLeft.rotateAngleX = MathHelper.sin(f / 1.5F + 3F * (float) Math.PI / 2F) * 2.1F * f1 + 0F;
        backLegRightMiddle.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI * 7F / 4F) * 1.4F * f1 + 0F;
        backLegRightBottom.rotateAngleX = -MathHelper.sin(f / 1.5F + (float) Math.PI * 7F / 4F) * 1.4F * f1 - 22F / 180F * (float) Math.PI;
        toesBackRight.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI * 7F / 4F) * 2.1F * f1 + 0F;
        backLegLeftMiddle.rotateAngleX = MathHelper.sin(f / 1.5F + 3f * (float) Math.PI / 4F) * 1.4F * f1 + 0F;
        backLegLeftBottom.rotateAngleX = -MathHelper.sin(f / 1.5F + 3f * (float) Math.PI / 4F) * 1.4F * f1 - 22F / 180F * (float) Math.PI;
        toesBackLeft.rotateAngleX = MathHelper.sin(f / 1.5F + 3f * (float) Math.PI / 4F) * 2.1F * f1 + 0F;

        /*
        frontLegLeftTop.rotateAngleX = MathHelper.cos(f / 1.5F + 3F * (float) Math.PI / 2F) * 0.7F * f1 + 0.3490659F;
        frontLegRightTop.rotateAngleX = MathHelper.cos(f / 1.5F + (float) Math.PI / 2F) * 0.7F * f1 + 0.3490659F;
        backLegRightTop.rotateAngleX = MathHelper.cos(f / 1.5F + (float) Math.PI * 7F / 4F) * 0.7F * f1 - 0.1745329F;
        backLegLeftTop.rotateAngleX = MathHelper.cos(f / 1.5F + 3f * (float) Math.PI / 4F) * 0.7F * f1 - 0.1745329F;
        frontLegRightBottom.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI / 2F) * 1.4F * f1;
        backLegRightMiddle.rotateAngleX = -MathHelper.sin(f / 1.5F + (float) Math.PI / 2F) * 0.7F * f1 - 0.3490659F;
        toesFrontRight.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI / 2F) * 2.1F * f1 + 1.134464F;
        frontLegLeftBottom.rotateAngleX = MathHelper.sin(f / 1.5F + 3F * (float) Math.PI / 2F) * 1.4F * f1;
        backLegLeftMiddle.rotateAngleX = -MathHelper.sin(f / 1.5F + 3F * (float) Math.PI / 2F) * 0.7F * f1 - 0.3490659F;
        toesFrontLeft.rotateAngleX = MathHelper.sin(f / 1.5F + 3F * (float) Math.PI / 2F) * 2.1F * f1 + 1.134464F;
        backLegRightMiddle.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI * 7F / 4F) * 1.4F * f1 + 0.5585054F;
        backLegRightBottom.rotateAngleX = -MathHelper.sin(f / 1.5F + (float) Math.PI * 7F / 4F) * 1.4F * f1 - 22F / 180F * (float) Math.PI;
        toesBackRight.rotateAngleX = MathHelper.sin(f / 1.5F + (float) Math.PI * 7F / 4F) * 2.1F * f1 + 1.134464F;
        backLegLeftMiddle.rotateAngleX = MathHelper.sin(f / 1.5F + 3f * (float) Math.PI / 4F) * 1.4F * f1 + 0.5585054F;
        backLegLeftBottom.rotateAngleX = -MathHelper.sin(f / 1.5F + 3f * (float) Math.PI / 4F) * 1.4F * f1 - 22F / 180F * (float) Math.PI;
        toesBackLeft.rotateAngleX = MathHelper.sin(f / 1.5F + 3f * (float) Math.PI / 4F) * 2.1F * f1 + 1.134464F;
        */
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
