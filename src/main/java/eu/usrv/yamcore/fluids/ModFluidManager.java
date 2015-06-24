package eu.usrv.yamcore.fluids;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
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
import eu.usrv.yamcore.events.BucketHandler;

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
		return GetModFluid(pModItem.getFluid().getUnlocalizedName());
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
			FluidCollection.put(pModFluid.getFluid().getUnlocalizedName(), pModFluid);
			_mLog.debug(String.format("Adding Fluid to managed registry: %s", pModFluid.getFluid().getUnlocalizedName()));
			return true;
		}
		else
		{
			_mLog.debug(String.format("Not adding Fluid to managed registry, since it's already registered! > %s", pModFluid.getFluid().getUnlocalizedName()));
			return false;
		}
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
					CreativeTabs tTargetTab = pTabManager.GetCreativeTabInstance(modFluid.getCreativeTabName());
					if (tTargetTab == null)
					{
						tTargetTab = CreativeTabs.tabMisc;
						_mLog.warn(String.format("CreativeTab name %s requested, but not registered in TabManager. Adding fluid %s to Tab 'Misc'", modFluid.getCreativeTabName(), modFluid.getUnlocalizedName()));
					}
					modFluid.setCreativeTab(tTargetTab);
					
					Fluid tFluid = modFluid.getFluid();
					if (tFluid == null)
					{
						_mLog.error(String.format("Unable to grab fluid for preregistered modFluid %s", modFluid.getUnlocalizedName()));
						continue;
					}
					_mLog.debug(String.format("Grabbed fluid: %s Preparing to register...", tFluid.getUnlocalizedName()));
					String tUnlocFluidName = tFluid.getUnlocalizedName().substring(6); // Remove fluid.
					modFluid.SetTextures(_mModID, tUnlocFluidName);
					modFluid.setBlockName(tUnlocFluidName);
					
					_mLog.debug(String.format("FluidBlock: %s Fluid: %s", modFluid.getUnlocalizedName(), tFluid.getUnlocalizedName()));

					if (modFluid.getRegisterBucket())
					{
						Item fluidBucket = new ModBucketItem(modFluid);
						fluidBucket.setUnlocalizedName(tUnlocFluidName + "_bucket").setContainerItem(Items.bucket);
						fluidBucket.setTextureName(String.format("%s:item%s_bucket", _mModID, tUnlocFluidName));
						fluidBucket.setCreativeTab(tTargetTab);
						
						FluidContainerRegistry.registerFluidContainer(tFluid, new ItemStack(fluidBucket), new ItemStack(Items.bucket));
						GameRegistry.registerItem(fluidBucket, _mModID + "_" + fluidBucket.getUnlocalizedName().substring(5));
						BucketHandler.INSTANCE.buckets.put(modFluid, fluidBucket);
					}
					
					Block tB = GameRegistry.registerBlock(modFluid, _mModID + "_" + modFluid.getUnlocalizedName().substring(5));
					if (tB == null)
						_mLog.error(String.format("Failed to register fluidblock %s", modFluid.getUnlocalizedName()));
					
					tFluid.setUnlocalizedName(tUnlocFluidName);
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
	
	public static Fluid GetNewFluid(String pFluidName)
	{
		Fluid tFluid = new Fluid(pFluidName);
		if (!FluidRegistry.registerFluid(tFluid))
		{
			YAMCore.instance.getLogger().error("Can't register fluid " + pFluidName + " to forge");
			return null;
		}
		else
		{
			YAMCore.instance.getLogger().debug("Registered " + pFluidName + " to forge");
			return tFluid;
		}
	}
}
