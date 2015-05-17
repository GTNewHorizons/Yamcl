package eu.usrv.yamcl.items;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import eu.usrv.yamcl.Yamcl;
import eu.usrv.yamcl.auxiliary.LogHelper;
import eu.usrv.yamcl.creativetabs.CreativeTabsManager;
import eu.usrv.yamcl.iface.IExtendedModItem;
import eu.usrv.yamcl.iface.IModItem;

public class ModItemManager {
	public Map<String, IModItem> ItemCollection = null;
	private CreativeTabsManager _mTabManager = null;
	private LogHelper _mLog = Yamcl.instance.getLogger();
	
	public ModItemManager()
	{
		ItemCollection = new HashMap<String, IModItem>();
	}
	
	/**
	 * Reveive instance of given ModItem
	 * @param pModItem
	 * @return
	 */
	public Item GetModItem(IModItem pModItem)
	{
		return GetModItem(pModItem.getUnlocItemName());
	}
	
	public Item GetModItem(String pModItemName)
	{
		if (ItemCollection.containsKey(pModItemName))
			return ItemCollection.get(pModItemName).getConstructedItem();
		else
			return null;		
	}
	
	public boolean AddItemToManagedRegistry(IModItem pModItem)
	{
		if (GetModItem(pModItem) == null)
		{
			ItemCollection.put(pModItem.getUnlocItemName(), pModItem);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Register Items in the forge registry
	 * @return
	 */
	public boolean RegisterItems()
	{
		try
		{
			for(IModItem modItem : ItemCollection.values())
			{
				CreativeTabs tTargetTab = _mTabManager.GetCreativeTabInstance(modItem.getCreativeTabName());
				if (tTargetTab == null)
				{
					_mLog.warn(String.format("CreativeTab name %s requested, but not registered in TabManager. Adding item %s to Tab 'Misc'", modItem.getCreativeTabName(), modItem.getConstructedItem().getUnlocalizedName()));
					modItem.setCreativeTab(CreativeTabs.tabMisc);
				}
				else
					modItem.setCreativeTab(tTargetTab);
				
				GameRegistry.registerItem(modItem.getConstructedItem(), modItem.getConstructedItem().getUnlocalizedName());
			}
			
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	
	/**
	 * Register a "non-enum" item to the gameregistry
	 * @param <T>
	 * @return
	 */
	public <T> boolean RegisterNonEnumItem(IExtendedModItem<T> pModItem)
	{ // Failed to define with "Class <? extends Item>", which would be a LOT easier to understand (and to code!) 
		try
		{
			CreativeTabs tTargetTab = _mTabManager.GetCreativeTabInstance(pModItem.getCreativeTabName());
			if (tTargetTab == null)
			{
				_mLog.warn(String.format("CreativeTab name %s requested, but not registered in TabManager. Adding item %s to Tab 'Misc'", pModItem.getCreativeTabName(), ((Item) (pModItem.getConstructedItem())).getUnlocalizedName()));
				tTargetTab = CreativeTabs.tabMisc;				
			}

			pModItem.setCreativeTab(tTargetTab);
			GameRegistry.registerItem((Item) pModItem.getConstructedItem(), pModItem.getUnlocalizedNameForRegistration());
			return true;
		}
		catch (Exception e)
		{
			// Todo: Logfile
			return false;
		}
	}
}
