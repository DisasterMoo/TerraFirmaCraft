/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.ForgeEventFactory;

import net.dries007.tfc.Constants;
import net.dries007.tfc.objects.LootTablesTFC;
import net.dries007.tfc.objects.entity.ai.*;
import net.dries007.tfc.objects.items.food.ItemFoodTFC;
import net.dries007.tfc.util.calendar.CalendarTFC;

import static net.dries007.tfc.objects.entity.animal.EntityAnimalTFC.getRandomGrowth;

@ParametersAreNonnullByDefault
public class EntityOcelotTFC extends EntityTameableTFC implements IAnimalTFC
{
    private static final int DAYS_TO_ADULTHOOD = 360;
    private static final int DAYS_TO_FULL_GESTATION = 70;

    public static void registerFixesOcelot(DataFixer fixer) { EntityLiving.registerFixesMob(fixer, EntityOcelotTFC.class); }

    private static final DataParameter<Integer> OCELOT_VARIANT;
    private EntityAIAvoidEntity avoidEntity;
    private EntityAITempt aiTempt;


    public EntityOcelotTFC(World worldIn)
   {
        this(worldIn, IAnimalTFC.Gender.fromBool(Constants.RNG.nextBoolean()), getRandomGrowth(DAYS_TO_ADULTHOOD));
    }

    public EntityOcelotTFC(World worldIn, IAnimalTFC.Gender gender, int birthDay)
    {
        super(worldIn, gender, birthDay);
        this.setSize(0.6F, 0.7F);
        this.setTamed(false);
    }



    //protected void entityInit() {
        //super.entityInit();
        //this.dataManager.register(OCELOT_VARIANT, 0);
    //}


    public boolean isValidSpawnConditions(Biome biome, float temperature, float rainfall)
    {
        return temperature > -20 && temperature < 20 && rainfall > 75;
    }


    public void birthChildren()
    {
        int numberOfChilds = 1 + rand.nextInt(1); //1-2
        for (int i = 0; i < numberOfChilds; i++)
        {
            EntityOcelotTFC baby = new EntityOcelotTFC(this.world, IAnimalTFC.Gender.fromBool(Constants.RNG.nextBoolean()), (int) CalendarTFC.PLAYER_TIME.getTotalDays());
            baby.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
            UUID uuid = this.getOwnerId();
            if (uuid != null)
            {
                baby.setOwnerId(uuid);
                baby.setTamed(true);
            }
            this.world.spawnEntity(baby);
        }
    }



    public long gestationDays()
    {
        return DAYS_TO_FULL_GESTATION;
    }

   //@Override
   // public float getEyeHeight()
    //{
        //return this.height * 0.6F;
    //}

    @Override
    public boolean processInteract(@Nonnull EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        if (this.isTamed())
        {
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack))
            {
                this.aiSit.setSitting(!this.isSitting());
            }
        }
        else if ((this.aiTempt == null || this.aiTempt.isRunning()) && itemstack.getItem() == Items.FISH && player.getDistanceSq(this) < 9.0D) {
            if (!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }
            if (!this.world.isRemote)
            {
                if (getFamiliarity() >= 0.3f && this.rand.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, player))
                {
                    this.setTamedBy(player);
                    this.setTameSkin(1 + this.world.rand.nextInt(3));
                    this.playTameEffect(true);
                    this.aiSit.setSitting(true);
                    this.world.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.processInteract(player, hand);
    }
    @Override
    public int getDaysToAdulthood()
    {
        return DAYS_TO_ADULTHOOD;
    }

    public void updateAITasks() {
        if (this.getMoveHelper().isUpdating()) {
            double d0 = this.getMoveHelper().getSpeed();
            if (d0 == 0.6D) {
                this.setSneaking(true);
                this.setSprinting(false);
            } else if (d0 == 1.33D) {
                this.setSneaking(false);
                this.setSprinting(true);
            } else {
                this.setSneaking(false);
                this.setSprinting(false);
            }
        } else {
            this.setSneaking(false);
            this.setSprinting(false);
        }

    }

    protected void initEntityAI()
    {
        this.aiSit = new EntityAISitTFC(this);
        this.aiTempt = new EntityAITempt(this, 0.6D, Items.FISH, true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiTempt);
        this.tasks.addTask(5, new EntityAIFollowOwnerTFC(this, 1.0D, 10.0F, 5.0F));
        //this.tasks.addTask(6, new EntityAIOcelotSit(this, 0.8D));
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
        this.tasks.addTask(8, new EntityAIOcelotAttack(this));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        //this.targetTasks.addTask(1, new EntityAITargetNonTamedTFC(this, EntityChickenTFC.class, false, (Predicate)null));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    public void fall(float distance, float damageMultiplier) {
    }



    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("CatType", this.getTameSkin());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setTameSkin(compound.getInteger("CatType"));
    }


    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        if (this.getTameSkin() == 0 && this.world.rand.nextInt(7) == 0) {
           for(int i = 0; i < 2; ++i) {
                net.dries007.tfc.objects.entity.animal.EntityOcelotTFC entityocelottfc = new net.dries007.tfc.objects.entity.animal.EntityOcelotTFC(this.world);
                entityocelottfc.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                entityocelottfc.setGrowingAge(-24000);
                this.world.spawnEntity(entityocelottfc);
           }
        }

        return livingdata;
   }

        @Nullable
        protected ResourceLocation getLootTable() {
            return LootTableList.ENTITIES_OCELOT;
        }

    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

        @Nullable
        protected SoundEvent getAmbientSound() {
            if (this.isTamed()) {
                if (this.isInLove()) {
                    return SoundEvents.ENTITY_CAT_PURR;
                } else {
                    return this.rand.nextInt(4) == 0 ? SoundEvents.ENTITY_CAT_PURREOW : SoundEvents.ENTITY_CAT_AMBIENT;
                }
            } else {
                return null;
            }
        }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else {
            if (this.aiSit != null) {
                this.aiSit.setSitting(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    //MAYBE KEEP THIS***
    //public boolean isNotColliding() {
    //if (this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
    //BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
    //if (blockpos.getY() < this.world.getSeaLevel()) {
    //return false;
    //}

    //IBlockState iblockstate = this.world.getBlockState(blockpos.down());
    //Block block = iblockstate.getBlock();
    //if (block == Blocks.GRASS || block.isLeaves(iblockstate, this.world, blockpos.down())) {
    //return true;
    //}
    // }

    // return false;
    //}

    //WANT TO KEEP THIS***
    //public String getName() {
    //if (this.hasCustomName()) {
    //return this.getCustomNameTag();
    //} else {
    //return this.isTamed() ? I18n.translateToLocal("entity.Cat.name") : super.getName();
    //}
    //}

       //WANT TO KEEP THIS****
    // protected void setupTamedAI() {
            //if (this.avoidEntity == null) {
                //this.avoidEntity = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D);
            //}

            //this.tasks.removeTask(this.avoidEntity);
            //if (!this.isTamed()) {
                //this.tasks.addTask(4, this.avoidEntity);
            //}

        //}

    public int getTameSkin() {
        return (Integer)this.dataManager.get(OCELOT_VARIANT);
    }

    public void setTameSkin(int skinId) {
        this.dataManager.set(OCELOT_VARIANT, skinId);
    }
    static {
        OCELOT_VARIANT = EntityDataManager.createKey(net.dries007.tfc.objects.entity.animal.EntityOcelotTFC.class, DataSerializers.VARINT);
    }
}