
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

public class FluorescentlampsBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public FluorescentlampsBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1f, 10f).lightLevel(s -> 15).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(-15.4, 14.7, 6.9, 15.5, 15.9, 10.1), box(-15.9, 14, 6.5, 16, 14.75, 10.5), box(-14.4, 13.3, 8.75, 14.5, 14.05, 9.9), box(-14.4, 13.3, 7.1, 14.5, 14.05, 8.25), box(-16, 13.5, 6.4, 16.1, 14, 6.75),
					box(-16, 13.5, 6.75, -15, 14, 10.3), box(15.1, 13.5, 6.75, 16.1, 14, 10.3), box(-16, 13.5, 10.3, 16.1, 14, 10.65));
			case NORTH -> Shapes.or(box(0.5, 14.7, 5.9, 31.4, 15.9, 9.1), box(0, 14, 5.5, 31.9, 14.75, 9.5), box(1.5, 13.3, 6.1, 30.4, 14.05, 7.25), box(1.5, 13.3, 7.75, 30.4, 14.05, 8.9), box(-0.1, 13.5, 9.25, 32, 14, 9.6),
					box(31, 13.5, 5.7, 32, 14, 9.25), box(-0.1, 13.5, 5.7, 0.9, 14, 9.25), box(-0.1, 13.5, 5.35, 32, 14, 5.7));
			case EAST -> Shapes.or(box(6.9, 14.7, 0.5, 10.1, 15.9, 31.4), box(6.5, 14, 0, 10.5, 14.75, 31.9), box(8.75, 13.3, 1.5, 9.9, 14.05, 30.4), box(7.1, 13.3, 1.5, 8.25, 14.05, 30.4), box(6.4, 13.5, -0.1, 6.75, 14, 32),
					box(6.75, 13.5, 31, 10.3, 14, 32), box(6.75, 13.5, -0.1, 10.3, 14, 0.9), box(10.3, 13.5, -0.1, 10.65, 14, 32));
			case WEST -> Shapes.or(box(5.9, 14.7, -15.4, 9.1, 15.9, 15.5), box(5.5, 14, -15.9, 9.5, 14.75, 16), box(6.1, 13.3, -14.4, 7.25, 14.05, 14.5), box(7.75, 13.3, -14.4, 8.9, 14.05, 14.5), box(9.25, 13.5, -16, 9.6, 14, 16.1),
					box(5.7, 13.5, -16, 9.25, 14, -15), box(5.7, 13.5, 15.1, 9.25, 14, 16.1), box(5.35, 13.5, -16, 5.7, 14, 16.1));
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
