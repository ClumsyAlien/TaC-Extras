package tac_extra.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/*
 * This is our config class. In here we can specify things that can be changed by the player.
 * The config allows users to take more control of the mod. For instance should a biome spawn?
 *
 * In this case, currently, I am only letting the player decide the chance of a dart being lost.
 */

/**
 * Author: Mr.Pineapple (ClumsyAlien, I am currently keeping the file before stashing in a note file, to potentially poke around with later)
 */
public class TimelessConfig {
    /*
     * This is an inner class of the config class.
     * This is the common config.
     * In the future we might have a client and server config.
     */
    public static class Common {
        //Initialise our value
        public ForgeConfigSpec.BooleanValue ammoProgressBar;

        //Constructor for the Common TimelessConfig
        Common(ForgeConfigSpec.Builder builder) {

            //What we will be pushing to the config, this has a title of common
            builder.push("client");
            {
                this.ammoProgressBar = builder.comment("Show the durabilityBar indicating ammo count per weapon").define("durabilityBar", true);
            }
            //Remember to pop this section
            builder.pop();
            builder.push("common");
            {
                
            }
        }
    }

    /*
     * Now we need to be able to access these values across our project.
     * We create a static variable of COMMON (same goes for server/client when added) so we can call the values in the mod.
     * Then we initialise them.
     */
    static final ForgeConfigSpec commonSpec;
    public static final TimelessConfig.Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
