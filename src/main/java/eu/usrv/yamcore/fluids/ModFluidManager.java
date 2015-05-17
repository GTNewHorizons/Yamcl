package eu.usrv.yamcore.fluids;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.creativetabs.CreativeTabsManager;
import eu.usrv.yamcore.items.ModSimpleBaseItem;

public class ModFluidManager {
	public Map<String, ModSimpleBaseFluid> FluidCollection = null;
	private LogHelper _mLog = YAMCore.instance.getLogger();
	private String _mModID;
	
	public ModFluidManager(String pModID)
	{
		FluidCollection = new HashMap<String, ModSimpleBaseFluid>();
		_mModID = pModID;
	}
	
	/**
	 * Reveive instance of given ModItem
	 * @param pModItem
	 * @return
	 */
	public ModSimpleBaseFluid GetModFluid(ModSimpleBaseFluid pModItem)
	{
		return GetModFluid(pModItem.getUnlocalizedName());
	}
	
	public ModSimpleBaseFluid GetModFluid(String pModItemName)
	{
		if (FluidCollection.containsKey(pModItemName))
			return FluidCollection.get(pModItemName);
		else
			return null;		
	}
	
	public boolean AddItemToManagedRegistry(ModSimpleBaseFluid pModFluid)
	{
		if (GetModFluid(pModFluid) == null)
		{
			FluidCollection.put(pModFluid.getUnlocalizedName(), pModFluid);
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
			for(ModSimpleBaseFluid modFluid : FluidCollection.values())
			{
				try
				{
					_mLog.info("Getting tab");
					CreativeTabs tTargetTab = pTabManager.GetCreativeTabInstance(modFluid.getCreativeTabName());
					if (tTargetTab == null)
					{
						_mLog.warn(String.format("CreativeTab name %s requested, but not registered in TabManager. Adding fluid %s to Tab 'Misc'", modFluid.getCreativeTabName(), modFluid.getUnlocalizedName()));
						modFluid.setCreativeTab(CreativeTabs.tabMisc);
					}
					else
					{
						_mLog.info("Setting tab");
						modFluid.setCreativeTab(tTargetTab);
					}
					
					Fluid tFluid = modFluid.getFluid();
					_mLog.info(String.format("FluidBlock: %s Fluid: %s", modFluid.getUnlocalizedName(), tFluid.getUnlocalizedName()));

					
					Item fluidBucket = new ModBucketItem(modFluid);
					fluidBucket.setUnlocalizedName(tFluid.getUnlocalizedName() + "_bucket").setContainerItem(Items.bucket);
					
					FluidContainerRegistry.registerFluidContainer(tFluid, new ItemStack(fluidBucket), new ItemStack(Items.bucket));
					tFluid.setUnlocalizedName(modFluid.getUnlocalizedName());

					GameRegistry.registerBlock(modFluid, _mModID + "_" + modFluid.getUnlocalizedName().substring(5));
					GameRegistry.registerItem(fluidBucket, "yourBucket");
				}
				catch (Exception e)
				{
					_mLog.error(String.format("Error while registering fluid %s, skipping", modFluid.getUnlocalizedName()));
					_mLog.DumpStack(e);
					continue;
				}
			}
			
			return true;
		}
		catch (Exception e)
		{
			_mLog.error(String.format("Error while registering fluids"));
			_mLog.DumpStack(e);
			return false;
		}
	}
}
