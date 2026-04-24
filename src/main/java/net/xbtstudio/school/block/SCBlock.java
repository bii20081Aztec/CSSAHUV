
package net.xbtstudio.school.block;

import org.checkerframework.checker.units.qual.s;

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

public class SCBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public SCBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1f, 10f).lightLevel(s -> 8).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(6.25, 15.3, 6.25, 9.75, 16.1, 9.75), box(6.8, 12.9, 7.2, 9.1, 15.2, 9.5), box(9.1, 12.8, 6.7, 9.4, 15.3, 9.2), box(6.5, 12.8, 6.7, 6.8, 15.3, 9.2));
			case NORTH -> Shapes.or(box(6.25, 15.3, 6.25, 9.75, 16.1, 9.75), box(6.9, 12.9, 6.5, 9.2, 15.2, 8.8), box(6.6, 12.8, 6.8, 6.9, 15.3, 9.3), box(9.2, 12.8, 6.8, 9.5, 15.3, 9.3));
			case EAST -> Shapes.or(box(6.25, 15.3, 6.25, 9.75, 16.1, 9.75), box(7.2, 12.9, 6.9, 9.5, 15.2, 9.2), box(6.7, 12.8, 6.6, 9.2, 15.3, 6.9), box(6.7, 12.8, 9.2, 9.2, 15.3, 9.5));
			case WEST -> Shapes.or(box(6.25, 15.3, 6.25, 9.75, 16.1, 9.75), box(6.5, 12.9, 6.8, 8.8, 15.2, 9.1), box(6.8, 12.8, 9.1, 9.3, 15.3, 9.4), box(6.8, 12.8, 6.5, 9.3, 15.3, 6.8));
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
