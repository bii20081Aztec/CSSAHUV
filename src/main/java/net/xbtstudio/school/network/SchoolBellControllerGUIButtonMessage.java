
package net.xbtstudio.school.network;

import net.xbtstudio.school.world.inventory.SchoolBellControllerGUIMenu;
import net.xbtstudio.school.procedures.StopSoundProcedure;
import net.xbtstudio.school.procedures.ShutdownProcedure;
import net.xbtstudio.school.procedures.RunningdrillmusicProcedure;
import net.xbtstudio.school.procedures.RecessProcedure;
import net.xbtstudio.school.procedures.EyeexercisesProcedure;
import net.xbtstudio.school.procedures.DancingYouthProcedure;
import net.xbtstudio.school.procedures.ColorfulSunshineProcedure;
import net.xbtstudio.school.procedures.ClassBeginOverProcedure;
import net.xbtstudio.school.SchoolMod;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SchoolBellControllerGUIButtonMessage {
	private final int buttonID, x, y, z;

	public SchoolBellControllerGUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public SchoolBellControllerGUIButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(SchoolBellControllerGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(SchoolBellControllerGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = SchoolBellControllerGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ClassBeginOverProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			RecessProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			StopSoundProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			DancingYouthProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			ColorfulSunshineProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			EyeexercisesProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			RunningdrillmusicProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			ShutdownProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SchoolMod.addNetworkMessage(SchoolBellControllerGUIButtonMessage.class, SchoolBellControllerGUIButtonMessage::buffer, SchoolBellControllerGUIButtonMessage::new, SchoolBellControllerGUIButtonMessage::handler);
	}
}
