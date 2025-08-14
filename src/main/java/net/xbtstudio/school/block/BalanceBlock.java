
package net.xbtstudio.school.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BalanceBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BalanceBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
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
			default -> Shapes.or(box(4, 0, 6, 12, 1, 9), box(5, 1, 6.25, 11, 1.75, 8.75), box(7.75, 1.75, 8.25, 8.25, 3.25, 8.5), box(10, 1.75, 7.4, 10.5, 3.25, 7.65), box(5.5, 1.75, 7.4, 6, 3.25, 7.65), box(4.5, 3.25, 6.4, 7, 3.6, 8.65),
					box(9, 3.25, 6.4, 11.5, 3.6, 8.65), box(5.5, 2.5, 8, 10.25, 3, 8.25), box(4.55, 2.5, 7.25, 11.45, 3, 7.5), box(3.55, 2.7, 7.3, 4.55, 2.8, 7.45), box(11.45, 2.7, 7.3, 12.45, 2.8, 7.45), box(7.75, 1.75, 6.5, 8.25, 4.75, 6.75),
					box(7.75, 1.75, 7, 8.25, 3, 7.25), box(7.95, 3, 7.1, 8.05, 4.75, 7.15), box(7.25, 4.5, 6.75, 8.75, 5, 7));
			case NORTH -> Shapes.or(box(4, 0, 7, 12, 1, 10), box(5, 1, 7.25, 11, 1.75, 9.75), box(7.75, 1.75, 7.5, 8.25, 3.25, 7.75), box(5.5, 1.75, 8.35, 6, 3.25, 8.6), box(10, 1.75, 8.35, 10.5, 3.25, 8.6), box(9, 3.25, 7.35, 11.5, 3.6, 9.6),
					box(4.5, 3.25, 7.35, 7, 3.6, 9.6), box(5.75, 2.5, 7.75, 10.5, 3, 8), box(4.55, 2.5, 8.5, 11.45, 3, 8.75), box(11.45, 2.7, 8.55, 12.45, 2.8, 8.7), box(3.55, 2.7, 8.55, 4.55, 2.8, 8.7), box(7.75, 1.75, 9.25, 8.25, 4.75, 9.5),
					box(7.75, 1.75, 8.75, 8.25, 3, 9), box(7.95, 3, 8.85, 8.05, 4.75, 8.9), box(7.25, 4.5, 9, 8.75, 5, 9.25));
			case EAST -> Shapes.or(box(6, 0, 4, 9, 1, 12), box(6.25, 1, 5, 8.75, 1.75, 11), box(8.25, 1.75, 7.75, 8.5, 3.25, 8.25), box(7.4, 1.75, 5.5, 7.65, 3.25, 6), box(7.4, 1.75, 10, 7.65, 3.25, 10.5), box(6.4, 3.25, 9, 8.65, 3.6, 11.5),
					box(6.4, 3.25, 4.5, 8.65, 3.6, 7), box(8, 2.5, 5.75, 8.25, 3, 10.5), box(7.25, 2.5, 4.55, 7.5, 3, 11.45), box(7.3, 2.7, 11.45, 7.45, 2.8, 12.45), box(7.3, 2.7, 3.55, 7.45, 2.8, 4.55), box(6.5, 1.75, 7.75, 6.75, 4.75, 8.25),
					box(7, 1.75, 7.75, 7.25, 3, 8.25), box(7.1, 3, 7.95, 7.15, 4.75, 8.05), box(6.75, 4.5, 7.25, 7, 5, 8.75));
			case WEST -> Shapes.or(box(7, 0, 4, 10, 1, 12), box(7.25, 1, 5, 9.75, 1.75, 11), box(7.5, 1.75, 7.75, 7.75, 3.25, 8.25), box(8.35, 1.75, 10, 8.6, 3.25, 10.5), box(8.35, 1.75, 5.5, 8.6, 3.25, 6), box(7.35, 3.25, 4.5, 9.6, 3.6, 7),
					box(7.35, 3.25, 9, 9.6, 3.6, 11.5), box(7.75, 2.5, 5.5, 8, 3, 10.25), box(8.5, 2.5, 4.55, 8.75, 3, 11.45), box(8.55, 2.7, 3.55, 8.7, 2.8, 4.55), box(8.55, 2.7, 11.45, 8.7, 2.8, 12.45), box(9.25, 1.75, 7.75, 9.5, 4.75, 8.25),
					box(8.75, 1.75, 7.75, 9, 3, 8.25), box(8.85, 3, 7.95, 8.9, 4.75, 8.05), box(9, 4.5, 7.25, 9.25, 5, 8.75));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
}
