
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

public class Classcard2Block extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public Classcard2Block() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(6, 3, 0, 9, 13, 1), box(6.31281, 3, 0.85622, 7.51281, 13, 3.98122), box(7.48719, 3, 0.85622, 8.68719, 13, 3.98122), box(6.67637, 3, 0.92593, 8.30137, 13, 4.05093));
			case NORTH -> Shapes.or(box(7, 3, 15, 10, 13, 16), box(8.48719, 3, 12.01878, 9.68719, 13, 15.14378), box(7.31281, 3, 12.01878, 8.51281, 13, 15.14378), box(7.69863, 3, 11.94907, 9.32363, 13, 15.07407));
			case EAST -> Shapes.or(box(0, 3, 7, 1, 13, 10), box(0.85622, 3, 8.48719, 3.98122, 13, 9.68719), box(0.85622, 3, 7.31281, 3.98122, 13, 8.51281), box(0.92593, 3, 7.69863, 4.05093, 13, 9.32363));
			case WEST -> Shapes.or(box(15, 3, 6, 16, 13, 9), box(12.01878, 3, 6.31281, 15.14378, 13, 7.51281), box(12.01878, 3, 7.48719, 15.14378, 13, 8.68719), box(11.94907, 3, 6.67637, 15.07407, 13, 8.30137));
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
