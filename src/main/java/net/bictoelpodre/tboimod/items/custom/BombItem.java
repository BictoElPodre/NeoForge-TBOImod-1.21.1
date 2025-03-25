package net.bictoelpodre.tboimod.items.custom;

import net.bictoelpodre.tboimod.block.ModBlocks;
import net.bictoelpodre.tboimod.component.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

import static net.bictoelpodre.tboimod.component.ModDataComponents.*;

public class BombItem extends Item {
    public BombItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);

        if(blockState.is(ModBlocks.BASEMENTROCK.get())) {
            if (!level.isClientSide) {
                level.playSound(null,player.getX(),player.getY(),player.getZ(),
                        SoundEvents.GENERIC_EXPLODE, player.getSoundSource(),1.0F,0.5F);
                ((ServerLevel) level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                        blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5,
                        50,
                        0.3, 0.3, 0.3,
                        0.1);
                player.getCooldowns().addCooldown(this, 20);
                level.removeBlock(blockPos,false);
                context.getItemInHand().set(BLOCK_STATE, null);
            }
            return InteractionResult.SUCCESS;
        }
        if (!level.isClientSide) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TNT_PRIMED, player.getSoundSource(),1.0F, 0.5F);
        }
        context.getItemInHand().set(BLOCK_STATE, blockState);

        return super.useOn(context);

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.thebindingofisaacmod.bombitem.tooltip"));

        if (stack.get(BLOCK_STATE) != null) {
            tooltipComponents.add(Component.literal("You can't destroy the " + stack.get(BLOCK_STATE).getBlock().getName().getString() + " with Bomb").withStyle(style -> style.withColor(0x5555FF).withItalic(true)));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
