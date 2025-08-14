
package net.xbtstudio.school.network;

import net.xbtstudio.school.world.inventory.MusicselectionpageMenu;
import net.xbtstudio.school.procedures.StopSoundProcedure;
import net.xbtstudio.school.procedures.ShutdownProcedure;
import net.xbtstudio.school.procedures.Oggta5Procedure;
import net.xbtstudio.school.procedures.Oggta4Procedure;
import net.xbtstudio.school.procedures.Oggta3Procedure;
import net.xbtstudio.school.procedures.Oggta2Procedure;
import net.xbtstudio.school.procedures.Oggta1Procedure;
import net.xbtstudio.school.procedures.DoctorSoundProcedure;
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
public class MusicselectionpageButtonMessage {
	private final int buttonID, x, y, z;

	public MusicselectionpageButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public MusicselectionpageButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(MusicselectionpageButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(MusicselectionpageButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = MusicselectionpageMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ShutdownProcedure.execute(entity);
		}
		if (buttonID == 1) {

			Oggta4Procedure.execute(entity);
		}
		if (buttonID == 2) {

			Oggta1Procedure.execute(entity);
		}
		if (buttonID == 3) {

			Oggta5Procedure.execute(entity);
		}
		if (buttonID == 4) {

			Oggta3Procedure.execute(entity);
		}
		if (buttonID == 5) {

			Oggta2Procedure.execute(entity);
		}
		if (buttonID == 6) {

			DoctorSoundProcedure.execute(world, x, y, z);
		}
		if (buttonID == 7) {

			StopSoundProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SchoolMod.addNetworkMessage(MusicselectionpageButtonMessage.class, MusicselectionpageButtonMessage::buffer, MusicselectionpageButtonMessage::new, MusicselectionpageButtonMessage::handler);
	}
}
