package eu.usrv.yamcore.items;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.creativetabs.CreativeTabsManager;
import eu.usrv.yamcore.iface.IExtendedModItem;
import eu.usrv.yamcore.iface.IModItem;

public class ModItemManager {
	public Map<String, ModSimpleBaseItem> ItemCollection = null;
	private LogHelper _mLog = YAMCore.instance.getLogger();
	private String _mModID;
	
	public ModItemManager(String pModID)
	{
		ItemCollection = new HashMap<String, ModSimpleBaseItem>();
		_mModID = pModID;
	}
	
	/**
	 * Reveive instance of given ModItem
	 * @param pModItem
	 * @return
	 */
	public Item GetModItem(ModSimpleBaseItem pModItem)
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
	
	public boolean AddItemToManagedRegistry(ModSimpleBaseItem pModItem)
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
	public boolean RegisterItems(CreativeTabsManager pTabManager)
	{
		try
		{
			for(ModSimpleBaseItem modItem : ItemCollection.values())
			{
				try
				{
					CreativeTabs tTargetTab = pTabManager.GetCreativeTabInstance(modItem.getCreativeTabName());
					if (tTargetTab == null)
					{
						_mLog.warn(String.format("CreativeTab name %s requested, but not registered in TabManager. Adding item %s to Tab 'Misc'", modItem.getCreativeTabName(), modItem.getConstructedItem().getUnlocalizedName()));
						modItem.setCreativeTab(CreativeTabs.tabMisc);
					}
					else
					{
						modItem.setCreativeTab(tTargetTab);
					}
					
					Item modItemItem = modItem.getConstructedItem();
					String unlocName = modItemItem.getUnlocalizedName();
					_mLog.debug(String.format("Item: %s ItemName: %s", modItemItem, unlocName));
					
					GameRegistry.registerItem(modItem.getConstructedItem(), modItem.getConstructedItem().getUnlocalizedName());
				}
				catch (Exception e)
				{
					_mLog.error(String.format("Error while registering item %s, skipping", modItem.getUnlocItemName()));
					_mLog.DumpStack(e);
					continue;
				}
			}
			
			return true;
		}
		catch (Exception e)
		{
			_mLog.error(String.format("Error while registering items"));
			_mLog.DumpStack(e);
			return false;
		}
	}

	
	/**
	 * Register a "non-enum" item to the gameregistry
	 * @param <T>
	 * @return
	 */
	public <T> boolean RegisterNonEnumItem(CreativeTabsManager pTabManager, IExtendedModItem<T> pModItem)
	{ // Failed to define with "Class <? extends Item>", which would be a LOT easier to understand (and to code!) 
		try
		{
			CreativeTabs tTargetTab = pTabManager.GetCreativeTabInstance(pModItem.getCreativeTabName());
			if (tTargetTab == null)
			{
				_mLog.warn(String.format("CreativeTab name %s requested, but not registered in TabManager. Adding item %s to Tab 'Misc'", pModItem.getCreativeTabName(), ((Item) (pModItem.getConstructedItem())).getUnlocalizedName()));
				tTargetTab = CreativeTabs.tabMisc;				
			}

			((Item) pModItem).setCreativeTab(tTargetTab);
			
			GameRegistry.registerItem((Item) pModItem.getConstructedItem(), pModItem.getUnlocalizedNameForRegistration());
			return true;
		}
		catch (Exception e)
		{
			_mLog.error(String.format("Error while registering item %s", pModItem.getUnlocalizedNameForRegistration()));
			_mLog.DumpStack(e);
			
			return false;
		}
	}
}
