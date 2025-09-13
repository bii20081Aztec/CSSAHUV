
package net.xbtstudio.school.block;

import net.xbtstudio.school.procedures.DoAlcoholLampBreakProcedure;
import net.xbtstudio.school.init.SchoolModBlocks;

import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.BiomeColors;

public class ScreenBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public ScreenBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.GLASS).strength(1.5f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
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
			default -> Shapes.or(box(-3, 3, 9.5, 19, 4.2, 10), box(-3, 16.5, 9.5, 19, 17, 10), box(-2.5, 4, 10, 18.5, 16.5, 11), box(18.5, 4.2, 9.5, 19, 16.5, 10), box(-3, 4.2, 9.5, -2.5, 16.5, 10), box(7, 1, 10, 9, 4, 11), box(4, 0, 9, 12, 1, 12),
					box(-2.5, 4.3, 9.9875, 18.5, 16.6, 10));
			case NORTH -> Shapes.or(box(-3, 3, 6, 19, 4.2, 6.5), box(-3, 16.5, 6, 19, 17, 6.5), box(-2.5, 4, 5, 18.5, 16.5, 6), box(-3, 4.2, 6, -2.5, 16.5, 6.5), box(18.5, 4.2, 6, 19, 16.5, 6.5), box(7, 1, 5, 9, 4, 6), box(4, 0, 4, 12, 1, 7),
					box(-2.5, 4.3, 6, 18.5, 16.6, 6.0125));
			case EAST -> Shapes.or(box(9.5, 3, -3, 10, 4.2, 19), box(9.5, 16.5, -3, 10, 17, 19), box(10, 4, -2.5, 11, 16.5, 18.5), box(9.5, 4.2, -3, 10, 16.5, -2.5), box(9.5, 4.2, 18.5, 10, 16.5, 19), box(10, 1, 7, 11, 4, 9), box(9, 0, 4, 12, 1, 12),
					box(9.9875, 4.3, -2.5, 10, 16.6, 18.5));
			case WEST -> Shapes.or(box(6, 3, -3, 6.5, 4.2, 19), box(6, 16.5, -3, 6.5, 17, 19), box(5, 4, -2.5, 6, 16.5, 18.5), box(6, 4.2, 18.5, 6.5, 16.5, 19), box(6, 4.2, -3, 6.5, 16.5, -2.5), box(5, 1, 7, 6, 4, 9), box(4, 0, 4, 7, 1, 12),
					box(6, 4.3, -2.5, 6.0125, 16.6, 18.5));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
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
		DoAlcoholLampBreakProcedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}

	@OnlyIn(Dist.CLIENT)
	public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
		event.getBlockColors().register((bs, world, pos, index) -> {
			return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D);
		}, SchoolModBlocks.SCREEN.get());
	}
}
