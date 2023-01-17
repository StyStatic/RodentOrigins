package com.data_stream.rodentorigins.item;

import net.minecraft.item.FoodComponent;

public class RodentFoodComponents {
    public static final FoodComponent MUFFIN = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.6F).build();
    public static final FoodComponent ACORN = (new FoodComponent.Builder()).hunger(1).saturationModifier(0).build();
}
