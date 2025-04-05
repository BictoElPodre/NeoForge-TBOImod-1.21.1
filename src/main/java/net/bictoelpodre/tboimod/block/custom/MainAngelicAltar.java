package net.bictoelpodre.tboimod.block.custom;

import com.mojang.serialization.MapCodec;
import net.bictoelpodre.tboimod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class MainAngelicAltar extends HorizontalDirectionalBlock {
    public static final MapCodec<MainAngelicAltar> CODEC = simpleCodec(MainAngelicAltar::new);

    public MainAngelicAltar(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {

        if (!level.isClientSide) {
            BlockPos leftPos = pos.relative(state.getValue(FACING).getCounterClockWise());
            BlockPos rightPos = pos.relative(state.getValue(FACING).getClockWise());
            if (level.getBlockState(leftPos).isAir()) {
                level.setBlock(leftPos, ModBlocks.LATERAL_ANGELIC_ALTAR.get().defaultBlockState().setValue(FACING, state.getValue(FACING)).rotate(level, leftPos, Rotation.CLOCKWISE_180), 3);
            }
            if (level.getBlockState(rightPos).isAir()) {
                level.setBlock(rightPos, ModBlocks.LATERAL_ANGELIC_ALTAR.get().defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!level.isClientSide && state.getBlock() != newState.getBlock()) {
            BlockPos leftPos = pos.relative(state.getValue(FACING).getCounterClockWise());
            BlockPos rightPos = pos.relative(state.getValue(FACING).getClockWise());

            if (level.getBlockState(leftPos).getBlock() == ModBlocks.LATERAL_ANGELIC_ALTAR.get()) {
                level.destroyBlock(leftPos, false);
            }
            if (level.getBlockState(rightPos).getBlock() == ModBlocks.LATERAL_ANGELIC_ALTAR.get()) {
                level.destroyBlock(rightPos, false);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
