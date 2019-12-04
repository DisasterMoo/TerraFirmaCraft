/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.model.animal;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.objects.entity.animal.EntityOcelotTFC;

@SideOnly(Side.CLIENT)
public class ModelOcelotTFC extends ModelBase
{
    private final ModelRenderer ocelotBackLeftLeg;
    private final ModelRenderer ocelotBackRightLeg;
    private final ModelRenderer ocelotFrontLeftLeg;
    private final ModelRenderer ocelotFrontRightLeg;
    private final ModelRenderer ocelotTail;
    private final ModelRenderer ocelotTail2;
    private final ModelRenderer ocelotHead;
    private final ModelRenderer ocelotBody;
    private int state = 1;

    public ModelOcelotTFC()
    {
        setTextureOffset("head.main", 0, 0);
        setTextureOffset("head.nose", 0, 24);
        setTextureOffset("head.ear1", 0, 10);
        setTextureOffset("head.ear2", 6, 10);
        ocelotHead = new ModelRenderer(this, "head");
        ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        ocelotHead.setRotationPoint(0.0F, 15.0F, -9.0F);

        ocelotBody = new ModelRenderer(this, 20, 0);
        ocelotBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
        ocelotBody.setRotationPoint(0.0F, 12.0F, -10.0F);

        ocelotTail = new ModelRenderer(this, 0, 15);
        ocelotTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        ocelotTail.rotateAngleX = 0.9F;
        ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);

        ocelotTail2 = new ModelRenderer(this, 4, 15);
        ocelotTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        ocelotTail2.setRotationPoint(0.0F, 20.0F, 14.0F);

        ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
        ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        ocelotBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);

        ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
        ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        ocelotBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);

        ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
        ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);

        ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
        ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 10.0F * scale, 4.0F * scale);
            ocelotHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            ocelotBody.render(scale);
            ocelotBackLeftLeg.render(scale);
            ocelotBackRightLeg.render(scale);
            ocelotFrontLeftLeg.render(scale);
            ocelotFrontRightLeg.render(scale);
            ocelotTail.render(scale);
            ocelotTail2.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            ocelotHead.render(scale);
            ocelotBody.render(scale);
            ocelotTail.render(scale);
            ocelotTail2.render(scale);
            ocelotBackLeftLeg.render(scale);
            ocelotBackRightLeg.render(scale);
            ocelotFrontLeftLeg.render(scale);
            ocelotFrontRightLeg.render(scale);
        }

    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        ocelotHead.rotateAngleX = headPitch * 0.017453292F;
        ocelotHead.rotateAngleY = netHeadYaw * 0.017453292F;
        if (this.state != 3)
        {
            ocelotBody.rotateAngleX = 1.5707964F;
            if (this.state == 2)
            {
                ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                ocelotBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 0.3F) * limbSwingAmount;
                ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F + 0.3F) * limbSwingAmount;
                ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * limbSwingAmount;
                ocelotTail2.rotateAngleX = 1.7278761F + 0.31415927F * MathHelper.cos(limbSwing) * limbSwingAmount;
            }
            else
            {
                ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                ocelotBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * limbSwingAmount;
                ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * limbSwingAmount;
                ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                if (this.state == 1)
                {
                    ocelotTail2.rotateAngleX = 1.7278761F + 0.7853982F * MathHelper.cos(limbSwing) * limbSwingAmount;
                }
                else
                {
                    ocelotTail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(limbSwing) * limbSwingAmount;
                }
            }
        }

    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        EntityOcelotTFC entityocelottfc = (EntityOcelotTFC) entitylivingbaseIn;

        this.ocelotBody.rotationPointY = 12.0F;
        this.ocelotBody.rotationPointZ = -10.0F;
        this.ocelotHead.rotationPointY = 15.0F;
        this.ocelotHead.rotationPointZ = -9.0F;
        this.ocelotTail.rotationPointY = 15.0F;
        this.ocelotTail.rotationPointZ = 8.0F;
        this.ocelotTail2.rotationPointY = 20.0F;
        this.ocelotTail2.rotationPointZ = 14.0F;
        this.ocelotFrontLeftLeg.rotationPointY = 13.8F;
        this.ocelotFrontLeftLeg.rotationPointZ = -5.0F;
        this.ocelotFrontRightLeg.rotationPointY = 13.8F;
        this.ocelotFrontRightLeg.rotationPointZ = -5.0F;
        this.ocelotBackLeftLeg.rotationPointY = 18.0F;
        this.ocelotBackLeftLeg.rotationPointZ = 5.0F;
        this.ocelotBackRightLeg.rotationPointY = 18.0F;
        this.ocelotBackRightLeg.rotationPointZ = 5.0F;
        this.ocelotTail.rotateAngleX = 0.9F;
        ModelRenderer var10000;
        if (entityocelottfc.isSneaking())
        {
            ++this.ocelotBody.rotationPointY;
            var10000 = this.ocelotHead;
            var10000.rotationPointY += 2.0F;
            ++this.ocelotTail.rotationPointY;
            var10000 = this.ocelotTail2;
            var10000.rotationPointY += -4.0F;
            var10000 = this.ocelotTail2;
            var10000.rotationPointZ += 2.0F;
            this.ocelotTail.rotateAngleX = 1.5707964F;
            this.ocelotTail2.rotateAngleX = 1.5707964F;
            this.state = 0;
        }
        else if (entityocelottfc.isSprinting())
        {
            this.ocelotTail2.rotationPointY = this.ocelotTail.rotationPointY;
            var10000 = this.ocelotTail2;
            var10000.rotationPointZ += 2.0F;
            this.ocelotTail.rotateAngleX = 1.5707964F;
            this.ocelotTail2.rotateAngleX = 1.5707964F;
            this.state = 2;
        }
        else if (entityocelottfc.isSitting())
        {
            this.ocelotBody.rotateAngleX = 0.7853982F;
            var10000 = this.ocelotBody;
            var10000.rotationPointY += -4.0F;
            var10000 = this.ocelotBody;
            var10000.rotationPointZ += 5.0F;
            var10000 = this.ocelotHead;
            var10000.rotationPointY += -3.3F;
            ++this.ocelotHead.rotationPointZ;
            var10000 = this.ocelotTail;
            var10000.rotationPointY += 8.0F;
            var10000 = this.ocelotTail;
            var10000.rotationPointZ += -2.0F;
            var10000 = this.ocelotTail2;
            var10000.rotationPointY += 2.0F;
            var10000 = this.ocelotTail2;
            var10000.rotationPointZ += -0.8F;
            this.ocelotTail.rotateAngleX = 1.7278761F;
            this.ocelotTail2.rotateAngleX = 2.670354F;
            this.ocelotFrontLeftLeg.rotateAngleX = -0.15707964F;
            this.ocelotFrontLeftLeg.rotationPointY = 15.8F;
            this.ocelotFrontLeftLeg.rotationPointZ = -7.0F;
            this.ocelotFrontRightLeg.rotateAngleX = -0.15707964F;
            this.ocelotFrontRightLeg.rotationPointY = 15.8F;
            this.ocelotFrontRightLeg.rotationPointZ = -7.0F;
            this.ocelotBackLeftLeg.rotateAngleX = -1.5707964F;
            this.ocelotBackLeftLeg.rotationPointY = 21.0F;
            this.ocelotBackLeftLeg.rotationPointZ = 1.0F;
            this.ocelotBackRightLeg.rotateAngleX = -1.5707964F;
            this.ocelotBackRightLeg.rotationPointY = 21.0F;
            this.ocelotBackRightLeg.rotationPointZ = 1.0F;
            this.state = 3;
        }
        else
        {
            this.state = 1;
        }

    }
}
