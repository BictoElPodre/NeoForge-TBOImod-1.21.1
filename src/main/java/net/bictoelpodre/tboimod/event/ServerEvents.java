package net.bictoelpodre.tboimod.event;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, value = Dist.DEDICATED_SERVER)
public class ServerEvents {

    public static void shootTear(boolean istearskeydown) {

        Minecraft.getInstance().player.sendSystemMessage(Component.literal("trying to shoot tears"));

        if (istearskeydown) {
            Player player = Minecraft.getInstance().player;
            Level level = Minecraft.getInstance().level;
            Vec3 look = player.getLookAngle();
            Snowball tears = new Snowball(level, look.x*2, look.y*2, look.z*2);
            tears.setPos(player.getX(), player.getEyeY(), player.getZ());
            level.addFreshEntity(tears);
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("tears shooted"));
        }
    }
}
