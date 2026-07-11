package cn.kegongteng.light_helmet.item;

import cn.kegongteng.light_helmet.LightHelmetMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final RegistryEntry<ArmorMaterial> LIGHT_HELMET_MATERIAL = register("light_helmet",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 2);
                map.put(ArmorItem.Type.CHESTPLATE, 3);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.EMPTY,
            List.of(new ArmorMaterial.Layer(LightHelmetMod.id("light_helmet"))),
            0.0f, 0.0f
    );

    public static final LightHelmetItem LIGHT_HELMET = new LightHelmetItem(
            LIGHT_HELMET_MATERIAL,
            new Item.Settings().maxCount(1)
    );

    public static void register() {
        Registry.register(Registries.ITEM, LightHelmetMod.id("light_helmet"), LIGHT_HELMET);
    }

    private static RegistryEntry<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> defense,
                                                          int enchantability,
                                                          net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> equipSound,
                                                          Supplier<Ingredient> repairIngredient,
                                                          List<ArmorMaterial.Layer> layers,
                                                          float toughness, float knockbackResistance) {
        ArmorMaterial material = new ArmorMaterial(defense, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance);
        return Registry.registerReference(Registries.ARMOR_MATERIAL, LightHelmetMod.id(name), material);
    }
}
