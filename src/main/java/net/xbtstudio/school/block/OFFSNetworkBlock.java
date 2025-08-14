
package net.xbtstudio.school.block;

import net.xbtstudio.school.procedures.NetworkProcedure;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class OFFSNetworkBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public OFFSNetworkBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SNOW).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(1, 9, 12, 15, 10, 13), box(1, 0, 3, 15, 10, 12), box(-0.5, 8, 6.5, 1, 14.5, 9.5), box(15, 8, 6.5, 16.5, 14.5, 9.5), box(1, 13, 6.5, 15, 14.5, 9.5));
			case NORTH -> Shapes.or(box(1, 9, 3, 15, 10, 4), box(1, 0, 4, 15, 10, 13), box(15, 8, 6.5, 16.5, 14.5, 9.5), box(-0.5, 8, 6.5, 1, 14.5, 9.5), box(1, 13, 6.5, 15, 14.5, 9.5));
			case EAST -> Shapes.or(box(12, 9, 1, 13, 10, 15), box(3, 0, 1, 12, 10, 15), box(6.5, 8, 15, 9.5, 14.5, 16.5), box(6.5, 8, -0.5, 9.5, 14.5, 1), box(6.5, 13, 1, 9.5, 14.5, 15));
			case WEST -> Shapes.or(box(3, 9, 1, 4, 10, 15), box(4, 0, 1, 13, 10, 15), box(6.5, 8, -0.5, 9.5, 14.5, 1), box(6.5, 8, 15, 9.5, 14.5, 16.5), box(6.5, 13, 1, 9.5, 14.5, 15));
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

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		NetworkProcedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
}
