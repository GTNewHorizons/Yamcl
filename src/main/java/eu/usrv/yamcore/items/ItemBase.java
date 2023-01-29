package eu.usrv.yamcore.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import eu.usrv.yamcore.auxiliary.enums.ItemRecipeBehaviorEnum;

/**
 * Created by Namikon on 01.08.2017.
 */
public class ItemBase extends Item {

    private final ItemRecipeBehaviorEnum _mIBehave;

    public ItemBase() {
        _mIBehave = ItemRecipeBehaviorEnum.Consume;
    }

    public ItemBase(ItemRecipeBehaviorEnum pBehavior) {
        _mIBehave = pBehavior;
    }

    public ItemRecipeBehaviorEnum getItemRecipeBehavior() {
        return _mIBehave;
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack pIs) {
        boolean tState = true;

        if (pIs != null && pIs.getItem() != null) {
            if (pIs.getItem() instanceof ItemBase) {
                if (((ItemBase) pIs.getItem()).getItemRecipeBehavior() == ItemRecipeBehaviorEnum.NoConsumeLeaveInGrid)
                    tState = false;
            }
        }

        return tState;
    }
}
