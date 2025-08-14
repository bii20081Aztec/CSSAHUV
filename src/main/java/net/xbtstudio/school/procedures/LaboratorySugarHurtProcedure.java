package net.xbtstudio.school.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

public class LaboratorySugarHurtProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer _player) {
			Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("school:gamble"));
			AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
			if (!_ap.isDone()) {
				for (String criteria : _ap.getRemainingCriteria())
					_player.getAdvancements().award(_adv, criteria);
			}
		}
		if (Mth.nextDouble(RandomSource.create(), 1, 10) <= 3) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeAllEffects();
			if (entity instanceof Player _player)
				_player.giveExperienceLevels(5);
			if (entity instanceof Player _player)
				_player.getFoodData().setFoodLevel(20);
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(20);
			entity.clearFire();
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 600, 1));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1));
		} else if (Mth.nextDouble(RandomSource.create(), 1, 10) >= 7) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeAllEffects();
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(1);
			if (entity instanceof Player _player)
				_player.getFoodData().setFoodLevel(0);
			if (entity instanceof Player _player)
				_player.giveExperienceLevels(-(entity instanceof Player _plr ? _plr.experienceLevel : 0));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 600, 1));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 1));
		}
	}
}
