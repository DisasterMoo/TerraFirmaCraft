/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */
package net.dries007.tfc.objects.entity.ai;

import net.dries007.tfc.api.types.IAnimalTFC;
import net.dries007.tfc.objects.entity.ai.EntityAIAttackMeleeTFC.AttackBehavior;
import net.dries007.tfc.objects.entity.animal.EntityLionTFC;
import net.dries007.tfc.objects.entity.animal.EntityAnimalTFC;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.network.datasync.EntityDataManager;

public class EntityAIAttackLionTFC extends EntityAIAttackMeleeTFC {

    private EntityLionTFC lion;
    private int attackTicks;
    protected AttackBehavior attackBehavior;
    EntityLivingBase target;
    int attackCountdown;


    @SuppressWarnings("unused")
    public EntityAIAttackLionTFC(EntityLionTFC lion, double speedIn, boolean useLongMemory)
    {
        this(lion, speedIn, useLongMemory, AttackBehavior.EVERYTIME);
    }

    public EntityAIAttackLionTFC(EntityLionTFC lion, double speedIn, boolean useLongMemory, AttackBehavior attackBehavior)
    {
        super(lion, speedIn, useLongMemory, attackBehavior);
        this.lion = lion;
        this.attackTicks = 0;
        this.attackBehavior = attackBehavior;
    }
    
    @Override
    public void startExecuting() {
        super.startExecuting();
        this.attackTicks = 0;
    }

    
    @Override
    public void resetTask() {
        super.resetTask();
        this.attackTicks = 0;
        this.lion.setMouthTicks(0);
    }

    @Override
    public void updateTask() {
        super.updateTask();
        ++this.attackTicks;
        this.lion.setMouthTicks(attackTicks);
    }

}
