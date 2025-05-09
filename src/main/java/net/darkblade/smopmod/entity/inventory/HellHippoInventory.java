package net.darkblade.smopmod.entity.inventory;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.darkblade.smopmod.item.ModItems;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class HellHippoInventory extends SimpleContainer {

    private final Hell_HippoEntity hippo;

    public HellHippoInventory(Hell_HippoEntity hippo, int size) {
        super(size);
        this.hippo = hippo;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == 1) {
            return stack.getItem() == ModItems.HELHIPPO_ARMOR.get(); // Solo permite esa armadura
        }
        return super.canPlaceItem(slot, stack);
    }
}
