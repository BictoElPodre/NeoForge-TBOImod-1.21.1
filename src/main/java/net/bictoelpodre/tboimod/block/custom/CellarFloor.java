package net.bictoelpodre.tboimod.block.custom;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Random;

public class CellarFloor extends Block {
    public static final EnumProperty<Variant> VARIANT = EnumProperty.create("variant", Variant.class);
    public static final BooleanProperty FLIPPED = BooleanProperty.create("flipped");

    public CellarFloor(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(VARIANT, Variant.V1)
                .setValue(FLIPPED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Random random = new Random(System.nanoTime());
        Variant randomVariant = Variant.getRandom(random);
        boolean flipped = random.nextBoolean();

        return this.defaultBlockState()
                .setValue(VARIANT, randomVariant)
                .setValue(FLIPPED, flipped);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(VARIANT, FLIPPED);
    }

    public enum Variant implements StringRepresentable {
        V1, V2, V3, V4, V5, V6, V7;

        public static Variant getRandom(Random random) {
            return values()[random.nextInt(values().length)];
        }

        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }
    }
}