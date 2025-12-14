package net.xbtstudio.school.procedures;

import net.xbtstudio.school.init.SchoolModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SetsquarProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y, z), SchoolModBlocks.SETSQUAREBLA.get().defaultBlockState(), 3);
	}
}
