
package net.xbtstudio.school.block;

import net.xbtstudio.school.procedures.ToiledoorProcedure;

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

public class ToiletdoorsBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public ToiletdoorsBlock() {
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
			default -> Shapes.or(box(0, 1, 10.7, 0.5, 32, 12), box(15.5, 1, 10.7, 16, 32, 12), box(14.5, 2, 10.7, 15.5, 30, 12), box(1.5, 2, 10.7, 14.5, 3.3, 12), box(1.5, 28.7, 10.7, 14.5, 30, 12), box(0.5, 30.7, 10.7, 15.5, 32, 12),
					box(0.5, 2, 10.7, 1.5, 30, 12), box(1, 3.3, 10.8, 15, 29.7, 11.6), box(12.6, 14.6, 10.2, 14.4, 16.4, 12), box(11.4, 16.6, 10, 12.4, 17.6, 12.5), box(13, 15, 12, 16, 16, 12.5));
			case NORTH -> Shapes.or(box(15.5, 1, 4, 16, 32, 5.3), box(0, 1, 4, 0.5, 32, 5.3), box(0.5, 2, 4, 1.5, 30, 5.3), box(1.5, 2, 4, 14.5, 3.3, 5.3), box(1.5, 28.7, 4, 14.5, 30, 5.3), box(0.5, 30.7, 4, 15.5, 32, 5.3),
					box(14.5, 2, 4, 15.5, 30, 5.3), box(1, 3.3, 4.4, 15, 29.7, 5.2), box(1.6, 14.6, 4, 3.4, 16.4, 5.8), box(3.6, 16.6, 3.5, 4.6, 17.6, 6), box(0, 15, 3.5, 3, 16, 4));
			case EAST -> Shapes.or(box(10.7, 1, 15.5, 12, 32, 16), box(10.7, 1, 0, 12, 32, 0.5), box(10.7, 2, 0.5, 12, 30, 1.5), box(10.7, 2, 1.5, 12, 3.3, 14.5), box(10.7, 28.7, 1.5, 12, 30, 14.5), box(10.7, 30.7, 0.5, 12, 32, 15.5),
					box(10.7, 2, 14.5, 12, 30, 15.5), box(10.8, 3.3, 1, 11.6, 29.7, 15), box(10.2, 14.6, 1.6, 12, 16.4, 3.4), box(10, 16.6, 3.6, 12.5, 17.6, 4.6), box(12, 15, 0, 12.5, 16, 3));
			case WEST -> Shapes.or(box(4, 1, 0, 5.3, 32, 0.5), box(4, 1, 15.5, 5.3, 32, 16), box(4, 2, 14.5, 5.3, 30, 15.5), box(4, 2, 1.5, 5.3, 3.3, 14.5), box(4, 28.7, 1.5, 5.3, 30, 14.5), box(4, 30.7, 0.5, 5.3, 32, 15.5),
					box(4, 2, 0.5, 5.3, 30, 1.5), box(4.4, 3.3, 1, 5.2, 29.7, 15), box(4, 14.6, 12.6, 5.8, 16.4, 14.4), box(3.5, 16.6, 11.4, 6, 17.6, 12.4), box(3.5, 15, 13, 4, 16, 16));
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
		ToiledoorProcedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
}
