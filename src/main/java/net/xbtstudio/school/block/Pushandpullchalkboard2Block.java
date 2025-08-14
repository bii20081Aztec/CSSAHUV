
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

public class Pushandpullchalkboard2Block extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public Pushandpullchalkboard2Block() {
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
			default -> Shapes.or(box(1, 0, 0, 31, 1, 2), box(1, 1, 0, 31, 28, 1), box(0, 0, 0, 1, 29, 2), box(31, 0, 0, 32, 29, 2), box(1, 28, 0, 31, 29, 2));
			case NORTH -> Shapes.or(box(-15, 0, 14, 15, 1, 16), box(-15, 1, 15, 15, 28, 16), box(15, 0, 14, 16, 29, 16), box(-16, 0, 14, -15, 29, 16), box(-15, 28, 14, 15, 29, 16));
			case EAST -> Shapes.or(box(0, 0, -15, 2, 1, 15), box(0, 1, -15, 1, 28, 15), box(0, 0, 15, 2, 29, 16), box(0, 0, -16, 2, 29, -15), box(0, 28, -15, 2, 29, 15));
			case WEST -> Shapes.or(box(14, 0, 1, 16, 1, 31), box(15, 1, 1, 16, 28, 31), box(14, 0, 0, 16, 29, 1), box(14, 0, 31, 16, 29, 32), box(14, 28, 1, 16, 29, 31));
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
