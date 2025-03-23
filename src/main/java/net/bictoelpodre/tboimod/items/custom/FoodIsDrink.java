package net.bictoelpodre.tboimod.items.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class FoodIsDrink extends Item {

    public FoodIsDrink(Properties properties) {
        super(properties);
    }
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }
}
