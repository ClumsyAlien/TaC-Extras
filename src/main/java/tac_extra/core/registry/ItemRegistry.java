package tac_extra.core.registry;

import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tac_extra.core.tac_extra;


/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */
@SuppressWarnings({"NullableProblems", "unused"})
public class ItemRegistry {
	
	// Code saved for later, this is for registering your own attachment attribute to add on to, this one increases accuracy by 25%
	
	//public static final IGunModifier COMPENSATOR_MODIFIER = new IGunModifier() {
	//    public float modifyProjectileSpread(float spread) {
	//        return spread * 0.75F;
	//    }
	//};
	
	
	// Create an object to hold all your items to be registered!
	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, tac_extra.ID);
	/*
		Register each item, your variable name can be whatever you wish, as long as you keep track of your variable name incase you wish to add more functionality to your weapon!

			~~~RegistryObject<GunItem>~~~
		Think of a RegistryObject as a new anything we add on to Minecraft, the <> is what type of something we wish to add!

		For this example I am creating a GunItem! This is from the Crayfish mod, we can reuse the GunItem class to create new Guns for our addon, placing our class type in the <>
		will tell Minecraft to create a new GunItem (<GunItem>), and search of all it's resources!

			~~~ITEM_REGISTRY.register("m1911", () -> new GunItem(new Item.Properties().group(tac_extra.GROUP))~~~
		Now we actually add the item into Minecraft! This will simply tell Minecraft that we expect a new item to be created, the "m1911" string is what I would call, the internal name!

		The internal name is what I call this piece, your internal name is what you will use to define the actual models, and data for your new item!

		(your_internalName).json would be an example for your file name, when creating a GunItem you are required to have both a model defined, and a data file defined!
		Take a look at my project structure, I would recommend you create something very similar, only with replacing "tac_extra" with your own modId!


		() -> new GunItem(new Item.Properties().group(tac_extra.GROUP)));

		The end of our .register(); method is where we actually finish up creating our GunItem! First we use a lambda to create our new GunItem with the bare minimum...

		~~~new GunItem(new Item.Properties().group(tac_extra.GROUP)~~~
		Here we are also saying the GunItem can only be in a stack of 1 (Unstackable) and we can also find it in the creative inventory via our own created Group!

		Now this is where I ClumsyAlien do something tricky and well, hacky, I do not recommend doing this at all and keeping your new GunItem registry looking like this...
		~~~public static final RegistryObject<GunItem> M1911 = ITEM_REGISTRY.register("m1911", () -> new GunItem(new Item.Properties().group(tac_extra.GROUP)));~~~

	------------------------------------------------------------------------------------------------------------------------------- If creating a basic weapon stop here!

		Once you understand the basic concept of adding an item we can talk about adding extra pieces onto our new Item or in this case, GunItem (GunItem implements Item, we have all the same possibilities as an item)
			Here I actually give Minecraft an extra little instruction, for this example when my m1911 gets enchanted, it will not render the enchantment glow no matter what!

			(sticking this right after running our lambda will allow adding methods / overrides without creating our own GunItem! Currently I will use this as development of my own
			GunTypes are not ready, and I only wish to add one extra detail to the current guns)*/

	public static final RegistryObject<TimelessGunItem> M1894 = ITEM_REGISTRY.register("m1894", TimelessGunItem::new);

	//public static final RegistryObject<TimelessOldRifleGunItem> M1894 = ITEM_REGISTRY.register("m1894", TimelessOldRifleGunItem::new); TimelessOldRifleGunItem allows the oldScope attachment to be accessible
	//public static final RegistryObject<TimelessPistolGunItem> M1894 = ITEM_REGISTRY.register("m1894", TimelessPistolGunItem::new); TimelessPistolGunItem allows the pistolScope and pistolBarrel to be accessible

	/*public static final RegistryObject<TimelessGunItem> M1911_NETHER = ITEM_REGISTRY.register("m1911_nether", () -> new TimelessGunItem(Properties::isImmuneToFire) {
		public int getItemEnchantability() {
			return 12;
		}
	});*/

	// Here I also create some new Ammunition for my mod! Not a necessary piece as you can continue using the original "tac:" ammo!
	/*public static final RegistryObject<TimelessAmmoItem> MAGNUM_BULLET = ITEM_REGISTRY.register("magnumround", TimelessAmmoItem::new);*/
	
}