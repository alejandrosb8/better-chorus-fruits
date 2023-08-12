package com.alejandro.better_chorus_fruits.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModFoods {

    public static final FoodProperties GOLDEN_CHORUS_FRUIT =
            new FoodProperties.Builder().nutrition(4).saturationMod(1.2F).alwaysEat().build();
}
