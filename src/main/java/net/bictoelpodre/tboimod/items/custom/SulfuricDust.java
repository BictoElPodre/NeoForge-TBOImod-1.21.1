
package net.bictoelpodre.tboimod.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SulfuricDust extends Item {
    public SulfuricDust(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);

        if(blockState.is(Blocks.WATER_CAULDRON)) {
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
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);

    }
}
