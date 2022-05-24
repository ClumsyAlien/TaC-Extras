package tac_extra.core;


import com.tac.guns.client.render.gun.IOverrideModel;
import com.tac.guns.client.render.gun.ModelOverrides;
import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tac_extra.core.registry.ItemRegistry;
import tac_extra.core.registry.SoundRegistry;

import java.lang.reflect.Field;
import java.util.Locale;


/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */
@Mod(tac_extra.ID)
public class tac_extra {
    //This variable is our mods ID - this must be coherent across the project
    public static final String ID = "tac_extra";

    /*
     * This is our creative tab that we will add our items to.
     * If you wanted, you could just add them to the Gun Mods tab.
     * We pass in our ID to this so we can name it in the lang file.
     */
    public static final ItemGroup GROUP = new ItemGroup(ID) {
        //Here we create the icon for the tab
        //If you wanted a normal item here then you can just return an ItemStack
        @Override
        public ItemStack createIcon() {
            //Get the Item in a new ItemStack
            ItemStack stack = new ItemStack(ItemRegistry.M1894.get());
            //Here we add ammunition to the gun so it doesn't have the re-fill bar under the item
            stack.getOrCreateTag().putInt("AmmoCount", ItemRegistry.M1894.get().getGun().getReloads().getMaxAmmo());
            //We now return the stack which has added ammunition
            return stack;
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    // Initialize the mod, and all event listeners.
    public tac_extra() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TimelessConfig.commonSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        //EntityRegistry.ENTITY_REGISTRY.register(bus);
        ItemRegistry.ITEM_REGISTRY.register(bus);
        SoundRegistry.SOUND_REGISTRY.register(bus);
        bus.addListener(this::clientSetup);
        // bus.addListener(this::clientLoad);
    }

    void clientSetup(FMLClientSetupEvent event) {
        for (Field field : ItemRegistry.class.getDeclaredFields()) {
            RegistryObject<?> object;
            try {
                object = (RegistryObject<?>) field.get(null);
            } catch (ClassCastException | IllegalAccessException e) {
                continue;
            }
            if (TimelessGunItem.class.isAssignableFrom(object.get().getClass())) {
                try {
                    ModelOverrides.register(
                            (Item) object.get(),
                            (IOverrideModel) Class.forName("tac_extra.client.render.gun.model." + field.getName().toLowerCase(Locale.ENGLISH) + "_animation").newInstance()
                    );
                } catch (ClassNotFoundException e) {
                    LOGGER.warn("Could not load animations for gun - " + field.getName());
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        /*RenderTypeLookup.setRenderLayer(TimelessBlocks.MAGNUMBOX.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_45.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_WIN_30.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_308.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_556.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_9.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TimelessBlocks.BOX_10g.get(), RenderType.getCutout());*/
    }


}
