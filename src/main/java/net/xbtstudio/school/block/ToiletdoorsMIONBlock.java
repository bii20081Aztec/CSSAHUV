
package net.xbtstudio.school.block;

import net.xbtstudio.school.procedures.ToiledoorsMIProcedure;

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

public class ToiletdoorsMIONBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public ToiletdoorsMIONBlock() {
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
			default -> Shapes.or(box(0, 1, 10.7, 0.5, 18, 12), box(15.5, 1, 10.7, 16, 18, 12), box(0.5, 2, 10.5, 1.8, 18, 11.5), box(0.5, 2, -3.5, 1.8, 18, -2.5), box(0.5, 2, -2.5, 1.8, 3.3, 10.5), box(0.5, 16.7, -2.5, 1.8, 18, 10.5),
					box(0, 14.6, -2.4, 1.8, 16.4, -0.6), box(1.8, 15, -2, 2.3, 18, -1), box(0.7, 3.3, -3, 1.5, 17.7, 11));
			case NORTH -> Shapes.or(box(15.5, 1, 4, 16, 18, 5.3), box(0, 1, 4, 0.5, 18, 5.3), box(14.2, 2, 4.5, 15.5, 18, 5.5), box(14.2, 2, 18.5, 15.5, 18, 19.5), box(14.2, 2, 5.5, 15.5, 3.3, 18.5), box(14.2, 16.7, 5.5, 15.5, 18, 18.5),
					box(14.2, 14.6, 16.6, 16, 16.4, 18.4), box(13.7, 15, 17, 14.2, 18, 18), box(14.5, 3.3, 5, 15.3, 17.7, 19));
			case EAST -> Shapes.or(box(10.7, 1, 15.5, 12, 18, 16), box(10.7, 1, 0, 12, 18, 0.5), box(10.5, 2, 14.2, 11.5, 18, 15.5), box(-3.5, 2, 14.2, -2.5, 18, 15.5), box(-2.5, 2, 14.2, 10.5, 3.3, 15.5), box(-2.5, 16.7, 14.2, 10.5, 18, 15.5),
					box(-2.4, 14.6, 14.2, -0.6, 16.4, 16), box(-2, 15, 13.7, -1, 18, 14.2), box(-3, 3.3, 14.5, 11, 17.7, 15.3));
			case WEST -> Shapes.or(box(4, 1, 0, 5.3, 18, 0.5), box(4, 1, 15.5, 5.3, 18, 16), box(4.5, 2, 0.5, 5.5, 18, 1.8), box(18.5, 2, 0.5, 19.5, 18, 1.8), box(5.5, 2, 0.5, 18.5, 3.3, 1.8), box(5.5, 16.7, 0.5, 18.5, 18, 1.8),
					box(16.6, 14.6, 0, 18.4, 16.4, 1.8), box(17, 15, 1.8, 18, 18, 2.3), box(5, 3.3, 0.7, 19, 17.7, 1.5));
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
		ToiledoorsMIProcedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
}
