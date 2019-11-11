/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.oredict.OreDictionary;

import net.dries007.tfc.Constants;
import net.dries007.tfc.api.capability.egg.CapabilityEgg;
import net.dries007.tfc.api.capability.egg.IEgg;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.LootTablesTFC;
import net.dries007.tfc.objects.entity.ai.EntityAIFindNest;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;

@ParametersAreNonnullByDefault
public class EntityChickenTFC extends EntityAnimalTFC implements IAnimalTFC
{
    private static final long DEFAULT_TICKS_TO_LAY_EGGS = ICalendar.TICKS_IN_DAY;
    private long lastLaying; //The last time(in ticks) this chicken has laid eggs
    private static final int DAYS_TO_ADULTHOOD = 124;
    private static final int DAYS_TO_HATCH_EGG = 21;

    private static int getRandomGrowth()
    {
        int lifeTimeDays = Constants.RNG.nextInt(DAYS_TO_ADULTHOOD * 4);
        return (int) (CalendarTFC.PLAYER_TIME.getTotalDays() - lifeTimeDays);
    }

    //Copy from vanilla's EntityChicken, used by renderer to properly handle wing flap
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;

    public EntityChickenTFC(World worldIn)
    {
        this(worldIn, Gender.fromBool(Constants.RNG.nextBoolean()),
            getRandomGrowth());
    }

    public EntityChickenTFC(World worldIn, Gender gender, int birthDay)
    {
        super(worldIn, gender, birthDay);
        this.setSize(0.9F, 0.9F);
        this.lastLaying = -1;
    }

    @Override
    public boolean isValidSpawnConditions(Biome biome, float temperature, float rainfall)
    {
        return temperature > 18 && rainfall > 350;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (this.getGender() == Gender.MALE && !this.world.isRemote && !this.isChild() && CalendarTFC.CALENDAR_TIME.getHourOfDay() == 6 && CalendarTFC.CALENDAR_TIME.getMinuteOfHour() == 0)
        {
            this.world.playSound(null, this.getPosition(), TFCSounds.ANIMAL_ROOSTER_CRY, SoundCategory.AMBIENT, 1.0F, 1.0F);
        }
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F)
        {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        this.wingRotation += this.wingRotDelta * 2.0F;
    }

    @Override
    public int getDaysToAdulthood()
    {
        return DAYS_TO_ADULTHOOD;
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setLong("laying", lastLaying);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1D));
        for (ItemStack is : OreDictionary.getOres("grain"))
        {
            Item item = is.getItem();
            this.tasks.addTask(3, new EntityAITempt(this, 1.1D, item, false));
        }
        this.tasks.addTask(4, new EntityAIFindNest(this, 1D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTablesTFC.ANIMALS_CHICKEN;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.lastLaying = nbt.getLong("laying");
    }

    @Override
    public Type getType()
    {
        return Type.OVIPAROUS;
    }

    @Override
    public boolean isReadyForAnimalProduct()
    {
        // Is ready for laying eggs?
        return this.getGender() == Gender.FEMALE && !this.isChild() && this.getFamiliarity() > 0.15f && CalendarTFC.PLAYER_TIME.getTicks() >= this.lastLaying + DEFAULT_TICKS_TO_LAY_EGGS;
    }

    @Override
    public List<ItemStack> getProducts()
    {
        List<ItemStack> eggs = new ArrayList<>();
        ItemStack egg = new ItemStack(Items.EGG);
        if (this.isFertilized())
        {
            IEgg cap = egg.getCapability(CapabilityEgg.CAPABILITY, null);
            if (cap != null)
            {
                cap.setFertilized(new EntityChickenTFC(this.world), DAYS_TO_HATCH_EGG + CalendarTFC.PLAYER_TIME.getTotalDays());
            }
        }
        this.setFertilized(false);
        eggs.add(egg);
        return eggs;
    }
}
