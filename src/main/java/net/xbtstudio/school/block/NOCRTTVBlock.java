
package net.xbtstudio.school.block;

import net.xbtstudio.school.procedures.CRTTV1Procedure;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class NOCRTTVBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public NOCRTTVBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(3, 4, 1, 13, 12, 3), box(0.5, 2, 12, 15.5, 15.5, 13), box(1, 2, 3, 15, 15, 12), box(2, 0, 3, 14, 2, 12), box(15.5, 0, 12, 16, 16, 13.5), box(0.5, 15.5, 12, 15.5, 16, 13.5), box(0.5, 0, 12, 15.5, 2, 13.5),
					box(0, 0, 12, 0.5, 16, 13.5), box(0.5, 2, 13, 15.5, 15.87386, 13.01121));
			case NORTH -> Shapes.or(box(3, 4, 13, 13, 12, 15), box(0.5, 2, 3, 15.5, 15.5, 4), box(1, 2, 4, 15, 15, 13), box(2, 0, 4, 14, 2, 13), box(0, 0, 2.5, 0.5, 16, 4), box(0.5, 15.5, 2.5, 15.5, 16, 4), box(0.5, 0, 2.5, 15.5, 2, 4),
					box(15.5, 0, 2.5, 16, 16, 4), box(0.5, 2, 2.98879, 15.5, 15.87386, 3));
			case EAST -> Shapes.or(box(1, 4, 3, 3, 12, 13), box(12, 2, 0.5, 13, 15.5, 15.5), box(3, 2, 1, 12, 15, 15), box(3, 0, 2, 12, 2, 14), box(12, 0, 0, 13.5, 16, 0.5), box(12, 15.5, 0.5, 13.5, 16, 15.5), box(12, 0, 0.5, 13.5, 2, 15.5),
					box(12, 0, 15.5, 13.5, 16, 16), box(13, 2, 0.5, 13.01121, 15.87386, 15.5));
			case WEST -> Shapes.or(box(13, 4, 3, 15, 12, 13), box(3, 2, 0.5, 4, 15.5, 15.5), box(4, 2, 1, 13, 15, 15), box(4, 0, 2, 13, 2, 14), box(2.5, 0, 15.5, 4, 16, 16), box(2.5, 15.5, 0.5, 4, 16, 15.5), box(2.5, 0, 0.5, 4, 2, 15.5),
					box(2.5, 0, 0, 4, 16, 0.5), box(2.98879, 2, 0.5, 3, 15.87386, 15.5));
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
		CRTTV1Procedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
}
