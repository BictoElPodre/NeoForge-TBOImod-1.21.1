package net.bictoelpodre.tboimod.block.custom;

import com.mojang.serialization.MapCodec;
import net.bictoelpodre.tboimod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class LateralAngelicAltar extends HorizontalDirectionalBlock {
    public static final MapCodec<MainAngelicAltar> CODEC = simpleCodec(MainAngelicAltar::new);

    public LateralAngelicAltar(Properties properties) {
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
}
