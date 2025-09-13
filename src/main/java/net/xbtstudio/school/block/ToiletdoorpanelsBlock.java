
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

public class ToiletdoorpanelsBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public ToiletdoorpanelsBlock() {
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
			default -> Shapes.or(box(6.5, 0, 14.5, 9, 1, 16), box(14.5, 0, -5.8, 16.7, 1, -3.3), box(-0.7, 0, -5.8, 1.5, 1, -3.3), box(7.1, 1, 15, 8.4, 32, 16), box(7.4, 3.3, -4.4, 8.2, 30.7, 15), box(1, 3.3, -5.2, 15, 30.7, -4.4),
					box(7.1, 2, -4, 8.4, 3.3, 15), box(1, 2, -5.3, 15, 3.3, -4), box(1, 30.7, -5.3, 15, 32, -4), box(7.1, 30.7, -4, 8.4, 32, 15), box(0, 1, -5.3, 1, 32, -4), box(15, 1, -5.3, 16, 32, -4));
			case NORTH -> Shapes.or(box(7, 0, 0, 9.5, 1, 1.5), box(-0.7, 0, 19.3, 1.5, 1, 21.8), box(14.5, 0, 19.3, 16.7, 1, 21.8), box(7.6, 1, 0, 8.9, 32, 1), box(7.8, 3.3, 1, 8.6, 30.7, 20.4), box(1, 3.3, 20.4, 15, 30.7, 21.2),
					box(7.6, 2, 1, 8.9, 3.3, 20), box(1, 2, 20, 15, 3.3, 21.3), box(1, 30.7, 20, 15, 32, 21.3), box(7.6, 30.7, 1, 8.9, 32, 20), box(15, 1, 20, 16, 32, 21.3), box(0, 1, 20, 1, 32, 21.3));
			case EAST -> Shapes.or(box(14.5, 0, 7, 16, 1, 9.5), box(-5.8, 0, -0.7, -3.3, 1, 1.5), box(-5.8, 0, 14.5, -3.3, 1, 16.7), box(15, 1, 7.6, 16, 32, 8.9), box(-4.4, 3.3, 7.8, 15, 30.7, 8.6), box(-5.2, 3.3, 1, -4.4, 30.7, 15),
					box(-4, 2, 7.6, 15, 3.3, 8.9), box(-5.3, 2, 1, -4, 3.3, 15), box(-5.3, 30.7, 1, -4, 32, 15), box(-4, 30.7, 7.6, 15, 32, 8.9), box(-5.3, 1, 15, -4, 32, 16), box(-5.3, 1, 0, -4, 32, 1));
			case WEST -> Shapes.or(box(0, 0, 6.5, 1.5, 1, 9), box(19.3, 0, 14.5, 21.8, 1, 16.7), box(19.3, 0, -0.7, 21.8, 1, 1.5), box(0, 1, 7.1, 1, 32, 8.4), box(1, 3.3, 7.4, 20.4, 30.7, 8.2), box(20.4, 3.3, 1, 21.2, 30.7, 15),
					box(1, 2, 7.1, 20, 3.3, 8.4), box(20, 2, 1, 21.3, 3.3, 15), box(20, 30.7, 1, 21.3, 32, 15), box(1, 30.7, 7.1, 20, 32, 8.4), box(20, 1, 0, 21.3, 32, 1), box(20, 1, 15, 21.3, 32, 16));
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
