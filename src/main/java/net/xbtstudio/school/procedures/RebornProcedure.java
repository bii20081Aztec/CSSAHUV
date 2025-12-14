package net.xbtstudio.school.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class RebornProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
					"gamemode creativer");
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u8BA9\u4F60\u91CD\u751F\u4E86\uFF1F\u6CA1\u7528\u7684\u6211\u5DF2\u7ECF\u628A\u4F60\u7269\u54C1\u680F\u548C\u80CC\u5305\u6E05\u7A7A\u4E86\uFF01"), true);
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (entity instanceof Player _player)
			_player.getInventory().clearContent();
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(Items.WHITE_BANNER).copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
	}
}
