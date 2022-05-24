package tac_extra.client;

import tac_extra.core.tac_extra;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/*
 * This class will be used to register special models (like the grenade launcher)
 * We can 'copy' from the SpecialModels class in the base gun mod as there
 * isn't an interface provided to implement.
 */

/**
 * Author: ClumsyAlien
 */
@EventBusSubscriber(modid = tac_extra.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public enum SpecialModels {
    // Each entry represents a special model piece
    GunPart1("gun_part1"),
    GunPart2("gun_part2");

    //Variables
    private final ResourceLocation modelLocation;
    private final boolean specialModel;
    @OnlyIn(Dist.CLIENT)
    private IBakedModel cachedModel;

    SpecialModels(String modelName) {
        //Get the file path for the special modes, and set them to true (the are going to be special models)
        this(new ResourceLocation(tac_extra.ID, "special/" + modelName), true);
    }

    //Second Constructor to feed variables
    SpecialModels(ResourceLocation resourceLocation, boolean specialModel) {
        this.modelLocation = resourceLocation;
        this.specialModel = specialModel;
    }

    //Get the item's model
    @OnlyIn(Dist.CLIENT)
    public IBakedModel getModel() {
        if (this.cachedModel == null) {
            IBakedModel model = Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
            if (model == Minecraft.getInstance().getModelManager().getMissingModel()) {
                return model;
            }
            this.cachedModel = model;
        }
        return this.cachedModel;
    }

    //Register a new model to that item
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void register(ModelRegistryEvent event) {
        for (SpecialModels model : values()) {
            if (model.specialModel) {
                ModelLoader.addSpecialModel(model.modelLocation);
            }
        }
    }
}
