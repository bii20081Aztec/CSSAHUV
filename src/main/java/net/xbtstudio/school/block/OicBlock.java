
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

public class OicBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public OicBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.ANVIL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(11, -16, -2, 32, 31, 18), box(11, 31, -3, 32, 32, 19), box(11.5, -13, -2.5, 31.5, 30, -1.5), box(11.5, -13, 17.5, 31.5, 30, 18.5), box(14, 4, -7, 29, 25, -2));
			case NORTH -> Shapes.or(box(-16, -16, -2, 5, 31, 18), box(-16, 31, -3, 5, 32, 19), box(-15.5, -13, 17.5, 4.5, 30, 18.5), box(-15.5, -13, -2.5, 4.5, 30, -1.5), box(-13, 4, 18, 2, 25, 23));
			case EAST -> Shapes.or(box(-2, -16, -16, 18, 31, 5), box(-3, 31, -16, 19, 32, 5), box(-2.5, -13, -15.5, -1.5, 30, 4.5), box(17.5, -13, -15.5, 18.5, 30, 4.5), box(-7, 4, -13, -2, 25, 2));
			case WEST -> Shapes.or(box(-2, -16, 11, 18, 31, 32), box(-3, 31, 11, 19, 32, 32), box(17.5, -13, 11.5, 18.5, 30, 31.5), box(-2.5, -13, 11.5, -1.5, 30, 31.5), box(18, 4, 14, 23, 25, 29));
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
