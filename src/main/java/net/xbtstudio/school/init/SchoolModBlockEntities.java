
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xbtstudio.school.init;

import net.xbtstudio.school.block.entity.SchoolBellControllerBlockEntity;
import net.xbtstudio.school.block.entity.PrinterBlockEntity;
import net.xbtstudio.school.block.entity.OutdoorspeakersBlockEntity;
import net.xbtstudio.school.block.entity.LoudspeakerBlockEntity;
import net.xbtstudio.school.block.entity.LaboratoryPoolBlockEntity;
import net.xbtstudio.school.block.entity.LaboratoryCabinetBlockEntity;
import net.xbtstudio.school.block.entity.FMRadioBlockEntity;
import net.xbtstudio.school.block.entity.BroadcastcrewBlockEntity;
import net.xbtstudio.school.SchoolMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

public class SchoolModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SchoolMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> LABORATORY_POOL = register("laboratory_pool", SchoolModBlocks.LABORATORY_POOL, LaboratoryPoolBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> LABORATORY_CABINET = register("laboratory_cabinet", SchoolModBlocks.LABORATORY_CABINET, LaboratoryCabinetBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SCHOOL_BELL_CONTROLLER = register("school_bell_controller", SchoolModBlocks.SCHOOL_BELL_CONTROLLER, SchoolBellControllerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PRINTER = register("printer", SchoolModBlocks.PRINTER, PrinterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> LOUDSPEAKER = register("loudspeaker", SchoolModBlocks.LOUDSPEAKER, LoudspeakerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> OUTDOORSPEAKERS = register("outdoorspeakers", SchoolModBlocks.OUTDOORSPEAKERS, OutdoorspeakersBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> FM_RADIO = register("fm_radio", SchoolModBlocks.FM_RADIO, FMRadioBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BROADCASTCREW = register("broadcastcrew", SchoolModBlocks.BROADCASTCREW, BroadcastcrewBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
