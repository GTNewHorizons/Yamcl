
package eu.usrv.yamcore.iface;

import net.minecraft.item.Item;

public interface IExtendedModItem<T extends Item> {

    T getConstructedItem();

    String getUnlocalizedNameForRegistration();

    String getCreativeTabName();
}
