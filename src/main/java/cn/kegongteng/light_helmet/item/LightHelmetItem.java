package cn.kegongteng.light_helmet.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class LightHelmetItem extends ArmorItem {
    public LightHelmetItem(net.minecraft.registry.entry.RegistryEntry<net.minecraft.item.ArmorMaterial> material, Settings settings) {
        super(material, ArmorItem.Type.HELMET, settings);
    }
}