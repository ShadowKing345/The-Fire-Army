package com.shadowprince345.thefirearmy.items.materials;

import com.shadowprince345.thefirearmy.TheFireArmy;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class ArmorMaterialPlatedIron implements IArmorMaterial {
    private int damageFactor = 16;

    @Override
    public int getDurability(EntityEquipmentSlot entityEquipmentSlot) {


        switch (entityEquipmentSlot) {
            case HEAD:
            case FEET:
                return 3 * damageFactor;
            case CHEST:
                return 7 * damageFactor;
            case LEGS:
                return 6 * damageFactor;
            default:
            return 0;
        }
    }

    @Override
    public int getDamageReductionAmount(EntityEquipmentSlot entityEquipmentSlot) {
        return damageFactor;
    }

    @Override
    public int getEnchantability() {
        return 9;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return TheFireArmy.getModId() + ":plated_iron_armor";
    }

    @Override
    public float getToughness() {
        return 1.0f;
    }
}
