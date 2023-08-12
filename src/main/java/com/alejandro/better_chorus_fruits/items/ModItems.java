package com.alejandro.better_chorus_fruits.items;

import com.alejandro.better_chorus_fruits.BetterChorusFruits;
import com.alejandro.better_chorus_fruits.items.custom.GoldenChorusFruit;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BetterChorusFruits.MOD_ID);

    public static final RegistryObject<Item> GOLDEN_CHORUS_FRUIT =
            ITEMS.register("golden_chorus_fruit", () -> new GoldenChorusFruit(new Item.Properties().food(ModFoods.GOLDEN_CHORUS_FRUIT)));

    public static final RegistryObject<Item> ENCHANTED_CHORUS_FRUIT =
            ITEMS.register("enchanted_chorus_fruit", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
