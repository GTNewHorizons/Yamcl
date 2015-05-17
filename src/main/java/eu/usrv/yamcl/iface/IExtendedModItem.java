package eu.usrv.yamcl.iface;

import net.minecraft.creativetab.CreativeTabs;

public interface IExtendedModItem<T>
{
	T getConstructedItem();
	String getUnlocalizedNameForRegistration();
	void setCreativeTab(CreativeTabs pTab);
	String getCreativeTabName();
}
