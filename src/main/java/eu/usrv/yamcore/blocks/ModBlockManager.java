package eu.usrv.yamcore.blocks;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.creativetabs.CreativeTabsManager;
import eu.usrv.yamcore.items.ModSimpleBaseItem;

public class ModBlockManager {
	public Map<String, ModSimpleBaseBlock> BlockCollection = null;
	private LogHelper _mLog = YAMCore.instance.getLogger();
	private String _mModID;
	
	public ModBlockManager(String pModID)
	{
		BlockCollection = new HashMap<String, ModSimpleBaseBlock>();
		_mModID = pModID;
	}
	
	/**
	 * Reveive instance of given ModItem
	 * @param pModItem
	 * @return
	 */
	public BlockBase GetModBlock(ModSimpleBaseBlock pModBlock)
	{
		return GetModBlock(pModBlock.getUnlocItemName());
	}
	
	public BlockBase GetModBlock(String pModBlockName)
	{
		if (BlockCollection.containsKey(pModBlockName))
			return BlockCollection.get(pModBlockName).getConstructedBlock();
		else
			return null;		
	}
	
	public boolean AddItemToManagedRegistry(ModSimpleBaseBlock pModBlock)
	{
		if (GetModBlock(pModBlock) == null)
		{
			BlockCollection.put(pModBlock.getUnlocItemName(), pModBlock);
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
			for(ModSimpleBaseBlock modBlock : BlockCollection.values())
			{
				try
				{
					_mLog.info("Getting tab");
					CreativeTabs tTargetTab = pTabManager.GetCreativeTabInstance(modBlock.getCreativeTabName());
					if (tTargetTab == null)
					{
						_mLog.warn(String.format("CreativeTab name %s requested, but not registered in TabManager. Adding Block %s to Tab 'Misc'", modBlock.getCreativeTabName(), modBlock.getConstructedBlock().getUnlocalizedName()));
						modBlock.setCreativeTab(CreativeTabs.tabMisc);
					}
					else
					{
						_mLog.info("Setting tab");
						modBlock.setCreativeTab(tTargetTab);
					}
					
					BlockBase modBlockBlock = modBlock.getConstructedBlock();
					String unlocName = modBlockBlock.getUnlocalizedName();
					_mLog.info(String.format("Block: %s BlockName: %s", modBlockBlock, unlocName));
					
					GameRegistry.registerBlock(modBlock.getConstructedBlock(), modBlock.getConstructedBlock().getUnlocalizedName());
				}
				catch (Exception e)
				{
					_mLog.error(String.format("Error while registering item %s, skipping", modBlock.getUnlocItemName()));
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
}
