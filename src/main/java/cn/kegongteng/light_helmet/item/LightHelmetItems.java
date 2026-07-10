package cn.kegongteng.light_helmet.item;

import cn.kegongteng.light_helmet.LightHelmet;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LightHelmetItems {
    public static final LightHelmetItem LIGHT_HELMET = new LightHelmetItem(
            ArmorMaterials.IRON,
            new Item.Settings().maxCount(1)
    );

    public static void register() {
        Registry.register(Registries.ITEM, LightHelmet.id("light_helmet"), LIGHT_HELMET);
    }
}