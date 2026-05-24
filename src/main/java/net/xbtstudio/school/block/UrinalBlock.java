
package net.xbtstudio.school.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
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

public class UrinalBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public UrinalBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(6, 0, 2, 10, 8, 6), box(2, 8, 0, 14, 12, 8), box(7.5, 24, 2, 8.5, 27, 3), box(2, 12, 0, 14, 24, 5), box(7.3, 27, 0, 8.8, 28.5, 3));
			case NORTH -> Shapes.or(box(6, 0, 10, 10, 8, 14), box(2, 8, 8, 14, 12, 16), box(7.5, 24, 13, 8.5, 27, 14), box(2, 12, 11, 14, 24, 16), box(7.2, 27, 13, 8.7, 28.5, 16));
			case EAST -> Shapes.or(box(2, 0, 6, 6, 8, 10), box(0, 8, 2, 8, 12, 14), box(2, 24, 7.5, 3, 27, 8.5), box(0, 12, 2, 5, 24, 14), box(0, 27, 7.2, 3, 28.5, 8.7));
			case WEST -> Shapes.or(box(10, 0, 6, 14, 8, 10), box(8, 8, 2, 16, 12, 14), box(13, 24, 7.5, 14, 27, 8.5), box(11, 12, 2, 16, 24, 14), box(13, 27, 7.3, 16, 28.5, 8.8));
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
