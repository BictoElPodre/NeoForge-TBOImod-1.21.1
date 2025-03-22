package net.bictoelpodre.tboimod.block.custom;

import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SulfurBlock extends Block {

    public static final IntegerProperty WEAR_LEVEL = IntegerProperty.create("wear_level", 0, 3);

    public SulfurBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WEAR_LEVEL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WEAR_LEVEL);
    }

    int wearCalcul = 0;

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        if (state.getValue(WEAR_LEVEL) == 0) {
            return super.getDrops(state, builder);
        }
        return Collections.emptyList();
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        int currentWear = state.getValue(WEAR_LEVEL);

        if (stack.is(Items.POTION) && stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).is(Potions.WATER)) {
            level.playSound(player, pos, SoundEvents.SAND_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!level.isClientSide) {
                ((ServerLevel) level).sendParticles(new DustParticleOptions(new Vector3f(1.0F,1.0F,0.0F), 1F),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        50,
                        0.3, 0.3, 0.3,
                        0.1);

                wearCalcul = wearCalcul + 9;

                player.addItem(new ItemStack(ModedItems.SULFURICDUST.get()));

                if (!player.isCreative()) {

                    stack.shrink(1);
                }
            }
        }

        else if (new Random().nextFloat() < 0.1f) {
            level.playSound(player, pos, SoundEvents.BRUSH_SAND_COMPLETED, SoundSource.BLOCKS, 1.0F, 1.0F);

            player.addItem(new ItemStack(ModedItems.SULFURDUST.get()));

            wearCalcul = wearCalcul + 1;
        }

        else {
            level.playSound(player, pos, SoundEvents.BRUSH_SAND, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        if (wearCalcul >=47) {
            wearCalcul = 0;

            level.setBlock(pos, state.setValue(WEAR_LEVEL, currentWear + 1), 3);
        }

        if (currentWear >=3) {
            level.removeBlock(pos, false);
        }
        return ItemInteractionResult.SUCCESS;
    }
}