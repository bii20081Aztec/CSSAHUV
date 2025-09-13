
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

public class LaboratoryChairBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public LaboratoryChairBlock() {
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
			default -> Shapes.or(box(12.25, 0, 11.25, 13.75, 12, 12.75), box(2.25, 0, 11.25, 3.75, 12, 12.75), box(12.25, 0, 4.35, 13.75, 12, 5.85), box(2.25, 0, 4.35, 3.75, 12, 5.85), box(1, 12, 3.5, 15, 13, 13.5), box(2.5, 8, 5.75, 3.5, 9, 11.25),
					box(12.5, 8, 5.75, 13.5, 9, 11.25), box(3.75, 8, 11.75, 12.25, 9, 12.5), box(3.75, 8, 4.75, 12.25, 9, 5.5));
			case NORTH -> Shapes.or(box(2.25, 0, 3.25, 3.75, 12, 4.75), box(12.25, 0, 3.25, 13.75, 12, 4.75), box(2.25, 0, 10.15, 3.75, 12, 11.65), box(12.25, 0, 10.15, 13.75, 12, 11.65), box(1, 12, 2.5, 15, 13, 12.5),
					box(12.5, 8, 4.75, 13.5, 9, 10.25), box(2.5, 8, 4.75, 3.5, 9, 10.25), box(3.75, 8, 3.5, 12.25, 9, 4.25), box(3.75, 8, 10.5, 12.25, 9, 11.25));
			case EAST -> Shapes.or(box(11.25, 0, 2.25, 12.75, 12, 3.75), box(11.25, 0, 12.25, 12.75, 12, 13.75), box(4.35, 0, 2.25, 5.85, 12, 3.75), box(4.35, 0, 12.25, 5.85, 12, 13.75), box(3.5, 12, 1, 13.5, 13, 15),
					box(5.75, 8, 12.5, 11.25, 9, 13.5), box(5.75, 8, 2.5, 11.25, 9, 3.5), box(11.75, 8, 3.75, 12.5, 9, 12.25), box(4.75, 8, 3.75, 5.5, 9, 12.25));
			case WEST -> Shapes.or(box(3.25, 0, 12.25, 4.75, 12, 13.75), box(3.25, 0, 2.25, 4.75, 12, 3.75), box(10.15, 0, 12.25, 11.65, 12, 13.75), box(10.15, 0, 2.25, 11.65, 12, 3.75), box(2.5, 12, 1, 12.5, 13, 15),
					box(4.75, 8, 2.5, 10.25, 9, 3.5), box(4.75, 8, 12.5, 10.25, 9, 13.5), box(3.5, 8, 3.75, 4.25, 9, 12.25), box(10.5, 8, 3.75, 11.25, 9, 12.25));
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
