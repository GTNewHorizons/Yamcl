package eu.usrv.yamcore.iface;

import net.minecraft.creativetab.CreativeTabs;

public interface IExtendedModItem<T>
{
	T getConstructedItem();
	String getUnlocalizedNameForRegistration();
	String getCreativeTabName();
}
