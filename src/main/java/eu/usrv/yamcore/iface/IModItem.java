package eu.usrv.yamcore.iface;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface IModItem {
	Item getConstructedItem();
	String getUnlocItemName();
	void setCreativeTab(CreativeTabs pTab);
	String getCreativeTabName();
}
