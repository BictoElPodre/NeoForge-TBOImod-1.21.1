package net.bictoelpodre.tboimod;


import net.bictoelpodre.tboimod.entity.TearsEntity;
import net.bictoelpodre.tboimod.entity.TearsModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TearsModel.LAYER_LOCATION, TearsModel::createBodyLayer);
    }
}
