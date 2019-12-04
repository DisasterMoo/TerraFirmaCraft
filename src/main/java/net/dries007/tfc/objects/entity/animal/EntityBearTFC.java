/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.Constants;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.LootTablesTFC;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.world.classic.biomes.BiomesTFC;

@ParametersAreNonnullByDefault
public class EntityBearTFC extends EntityAnimalMammal implements IMob
{
    private static final DataParameter<Boolean> IS_STANDING;
    private float clientSideStandAnimation0;
    private float clientSideStandAnimation;
    private int warningSoundTicks;
    private static final int DAYS_TO_ADULTHOOD = 1800;
    private static final int DAYS_TO_FULL_GESTATION = 210;

    @SuppressWarnings("unused")
    public EntityBearTFC(World worldIn)
    {
        this(worldIn, Gender.fromBool(Constants.RNG.nextBoolean()),
            getRandomGrowth(DAYS_TO_ADULTHOOD));
    }

    public EntityBearTFC(World worldIn, Gender gender, int birthDay)
    {
        super(worldIn, gender, birthDay);
        this.setSize(1.2F, 1.2F);
    }

    @Override
    public boolean isValidSpawnConditions(Biome biome, float temperature, float rainfall)
    {
        return (temperature > -15 && temperature < 15 && rainfall > 100) ||
            (temperature > -10 && temperature < 25 && biome == BiomesTFC.MOUNTAINS);
    }

    @Override
    public int getDaysToAdulthood()
    {
        return DAYS_TO_ADULTHOOD;
    }

    @Override
    public void birthChildren()
    {
        int numberOfChilds = 1; //one always
        for (int i = 0; i < numberOfChilds; i++)
        {
            EntityBearTFC baby = new EntityBearTFC(this.world, Gender.fromBool(Constants.RNG.nextBoolean()), (int) CalendarTFC.PLAYER_TIME.getTotalDays());
            baby.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
            this.world.spawnEntity(baby);
        }

    }

    @Override
    public long gestationDays()
    {
        return DAYS_TO_FULL_GESTATION;
    }

    @Override
    public boolean isFood(ItemStack it)
    {
        return it.getItem() == Items.FISH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return TFCSounds.ANIMAL_BEAR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return TFCSounds.ANIMAL_BEAR_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (getAge() == Age.CHILD ? 2 : 4));
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityBearTFC.AIMeleeAttack());
        this.tasks.addTask(1, new EntityBearTFC.AIPanic());
        this.tasks.addTask(2, new EntityAITempt(this, 1.1D, Items.FISH, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return Constants.RNG.nextInt(100) < 5 ? TFCSounds.ANIMAL_BEAR_CRY : TFCSounds.ANIMAL_BEAR_SAY;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTablesTFC.ANIMALS_BEAR;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
    }

    public boolean isStanding() {
        return (Boolean)this.dataManager.get(IS_STANDING);
    }
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(IS_STANDING, false);
    }

    public void setStanding(boolean standing) {
        this.dataManager.set(IS_STANDING, standing);
    }
    static {
        IS_STANDING = EntityDataManager.createKey(EntityBearTFC.class, DataSerializers.BOOLEAN);
    }

    @SideOnly(Side.CLIENT)
    public float getStandingAnimationScale(float p_189795_1_) {
        return (this.clientSideStandAnimation0 + (this.clientSideStandAnimation - this.clientSideStandAnimation0) * p_189795_1_) / 6.0F;
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote) {
            this.clientSideStandAnimation0 = this.clientSideStandAnimation;
            if (this.isStanding()) {
                this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation + 1.0F, 0.0F, 6.0F);
            } else {
                this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation - 1.0F, 0.0F, 6.0F);
            }
        }

        if (this.warningSoundTicks > 0) {
            --this.warningSoundTicks;
        }

    }

    protected void playWarningSound() {
        if (this.warningSoundTicks <= 0) {
            this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, 1.0F);
            this.warningSoundTicks = 40;
        }

    }

    class AIPanic extends EntityAIPanic {
        public AIPanic() {
            super(EntityBearTFC.this, 1.5D);
        }

        public boolean shouldExecute() {
            return !EntityBearTFC.this.isBurning() ? false : super.shouldExecute();
        }
    }

    class AIMeleeAttack extends EntityAIAttackMelee {
        public AIMeleeAttack() {
            super(EntityBearTFC.this, 1.25D, true);
        }

        protected void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            if (distToEnemySqr <= d0 && this.attackTick <= 0) {
                this.attackTick = 20;
                this.attacker.attackEntityAsMob(enemy);
                EntityBearTFC.this.setStanding(false);
            } else if (distToEnemySqr <= d0 * 2.0D) {
                if (this.attackTick <= 0) {
                    EntityBearTFC.this.setStanding(false);
                    this.attackTick = 20;
                }

                if (this.attackTick <= 10) {
                    EntityBearTFC.this.setStanding(true);
                    EntityBearTFC.this.playWarningSound();
                }
            } else {
                this.attackTick = 20;
                EntityBearTFC.this.setStanding(false);
            }

        }

        public void resetTask() {
            EntityBearTFC.this.setStanding(false);
            super.resetTask();
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget) {
            return (double)(4.0F + attackTarget.width);
        }
    }

}
