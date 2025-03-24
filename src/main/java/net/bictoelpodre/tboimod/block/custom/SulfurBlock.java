package net.bictoelpodre.tboimod.block.custom;

import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
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

    public static final IntegerProperty WEAR_LEVEL = IntegerProperty.create("wear_level", 0, 4);

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

    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int currentWear = state.getValue(WEAR_LEVEL);

        if (!level.isClientSide) {
            if (stack.is(Items.POTION) && stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).is(Potions.WATER)) {
                level.playSound(null, pos, SoundEvents.SAND_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                player.addItem(new ItemStack(ModedItems.SULFURICDUST.get()));

                wearCalcul = wearCalcul + 9;

                if (!player.isCreative()) {
                    stack.shrink(1);
                }
            } else if (new Random().nextFloat() < 0.1f) {
                Block.popResource(level, pos, new ItemStack(ModedItems.SULFURDUST.get()));

                level.playSound(null, pos, SoundEvents.BRUSH_SAND_COMPLETED, SoundSource.BLOCKS, 1.0F, 1.0F);

                wearCalcul = wearCalcul + 1;
            } else {
                level.playSound(null, pos, SoundEvents.BRUSH_SAND, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            if (wearCalcul >= 47) {
                wearCalcul = 0;

                level.setBlock(pos, state.setValue(WEAR_LEVEL, currentWear + 1), 4);

                level.playSound(null, pos, SoundEvents.POWDER_SNOW_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            if (currentWear >= 4) {
                level.removeBlock(pos, false);

                Block.popResource(level, pos, new ItemStack(ModedItems.SULFUR.get()));

                level.playSound(null, pos, SoundEvents.SAND_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            ((ServerLevel) level).sendParticles(new DustParticleOptions(new Vector3f(1.0F, 1.0F, 0.0F), 1F),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    50,
                    0.3, 0.3, 0.3,
                    0.1);
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (entity instanceof Player player) {

            if (!(entity.isSteppingCarefully())) {
                spawnParticles(level, player);
                applyBlindness(level, player);

            }
        }
        super.stepOn(level, pos, state, entity);
    }

    private void applyBlindness(Level level, Player player) {

        if (!level.isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0));

        }
    }

    private void spawnParticles(Level level, Player player) {

        if (!level.isClientSide()) {
            return;
        }

        for (int i = 0; i < 20; i++) {

            double offsetX = (level.random.nextDouble() - 0.5) * 2.5;
            double offsetY = (level.random.nextDouble() - 0.5) * 4;
            double offsetZ = (level.random.nextDouble() - 0.5) * 2.5;

            level.addParticle(
                    new DustParticleOptions(new Vector3f(1.0F, 1.0F, 0.0F), 1), // Polvo amarillo
                    player.getX() + offsetX, player.getY() + offsetY, player.getZ() + offsetZ,
                    0.0, 0.0, 0.0);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.thebindingofisaacmod.sulfurblockshiftdown.tooltip"));
        }

        else {
            tooltipComponents.add(Component.translatable("tooltip.thebindingofisaacmod.sulfurblock.tooltip"));
            tooltipComponents.add(Component.translatable("tooltip.thebindingofisaacmod.shift.tooltip"));

            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
    }
}