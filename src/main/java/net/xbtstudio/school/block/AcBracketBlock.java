
package net.xbtstudio.school.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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

public class AcBracketBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public AcBracketBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(13, 2, 0, 15, 15, 1), box(1, 2, 0, 3, 15, 1), box(1, 14, 1, 3, 15, 13), box(13, 14, 1, 15, 15, 13), box(13, 7, 2, 15, 8, 18), box(1, 7, 2, 3, 8, 18), box(3, 11, 0, 13, 12, 1), box(3, 14, 2, 13, 15, 4),
					box(3, 14, 9, 13, 15, 11), box(3, 3, 0, 13, 4, 1));
			case NORTH -> Shapes.or(box(1, 2, 15, 3, 15, 16), box(13, 2, 15, 15, 15, 16), box(13, 14, 3, 15, 15, 15), box(1, 14, 3, 3, 15, 15), box(1, 7, -2, 3, 8, 14), box(13, 7, -2, 15, 8, 14), box(3, 11, 15, 13, 12, 16),
					box(3, 14, 12, 13, 15, 14), box(3, 14, 5, 13, 15, 7), box(3, 3, 15, 13, 4, 16));
			case EAST -> Shapes.or(box(0, 2, 1, 1, 15, 3), box(0, 2, 13, 1, 15, 15), box(1, 14, 13, 13, 15, 15), box(1, 14, 1, 13, 15, 3), box(2, 7, 1, 18, 8, 3), box(2, 7, 13, 18, 8, 15), box(0, 11, 3, 1, 12, 13), box(2, 14, 3, 4, 15, 13),
					box(9, 14, 3, 11, 15, 13), box(0, 3, 3, 1, 4, 13));
			case WEST -> Shapes.or(box(15, 2, 13, 16, 15, 15), box(15, 2, 1, 16, 15, 3), box(3, 14, 1, 15, 15, 3), box(3, 14, 13, 15, 15, 15), box(-2, 7, 13, 14, 8, 15), box(-2, 7, 1, 14, 8, 3), box(15, 11, 3, 16, 12, 13), box(12, 14, 3, 14, 15, 13),
					box(5, 14, 3, 7, 15, 13), box(15, 3, 3, 16, 4, 13));
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
