
package net.xbtstudio.school.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class Pushandpullchalkboard4Block extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public Pushandpullchalkboard4Block() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(-15, 0, 2, 15, 1, 4), box(-15, 1, 2, 15, 28, 3), box(-16, 0, 2, -15, 29, 4), box(15, 0, 2, 16, 29, 4), box(-15, 28, 2, 15, 29, 4), box(-15, 0, 0, 15, 1, 2), box(-15, 1, 0, 15, 28, 1), box(-16, 0, 0, -15, 29, 2),
					box(15, 0, 0, 16, 29, 2), box(-15, 28, 0, 15, 29, 2));
			case NORTH -> Shapes.or(box(1, 0, 12, 31, 1, 14), box(1, 1, 13, 31, 28, 14), box(31, 0, 12, 32, 29, 14), box(0, 0, 12, 1, 29, 14), box(1, 28, 12, 31, 29, 14), box(1, 0, 14, 31, 1, 16), box(1, 1, 15, 31, 28, 16),
					box(31, 0, 14, 32, 29, 16), box(0, 0, 14, 1, 29, 16), box(1, 28, 14, 31, 29, 16));
			case EAST -> Shapes.or(box(2, 0, 1, 4, 1, 31), box(2, 1, 1, 3, 28, 31), box(2, 0, 31, 4, 29, 32), box(2, 0, 0, 4, 29, 1), box(2, 28, 1, 4, 29, 31), box(0, 0, 1, 2, 1, 31), box(0, 1, 1, 1, 28, 31), box(0, 0, 31, 2, 29, 32),
					box(0, 0, 0, 2, 29, 1), box(0, 28, 1, 2, 29, 31));
			case WEST -> Shapes.or(box(12, 0, -15, 14, 1, 15), box(13, 1, -15, 14, 28, 15), box(12, 0, -16, 14, 29, -15), box(12, 0, 15, 14, 29, 16), box(12, 28, -15, 14, 29, 15), box(14, 0, -15, 16, 1, 15), box(15, 1, -15, 16, 28, 15),
					box(14, 0, -16, 16, 29, -15), box(14, 0, 15, 16, 29, 16), box(14, 28, -15, 16, 29, 15));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}
