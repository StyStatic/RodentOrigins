package com.data_stream.rodentorigins.item;

import com.data_stream.rodentorigins.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RodentItems{

    public static final Item MUFFIN = registerItem("muffin",
            new Item(new Item.Settings().food(RodentFoodComponents.MUFFIN).group(ItemGroup.FOOD)));

    public static final Item ACORN = registerItem("acorn",
            new Item(new Item.Settings().food(RodentFoodComponents.ACORN).group(ItemGroup.FOOD)));

    public static final Item HEART = registerItem("heart",
            new Item(new Item.Settings()));

    public static final Item DISCO = registerItem("disco",
            new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Main.MODID, name), item);
    }

    public static void RegisterRodentItems() {
        Main.LOGGER.info("Registering Mod Items for " + Main.MODID);
    }
}
