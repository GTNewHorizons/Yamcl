package eu.usrv.yamcore.creativetabs;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabsManager {
	private Map<String, CreativeTabs> _mCreativeTabsMap = new HashMap<String, CreativeTabs>();
	
	/**
	 * Add new creativetab to local registry for later use
	 * @param pCreativeTab
	 * @return
	 */
	public boolean AddCreativeTab(ModCreativeTab pCreativeTab)
	{
		if (!_mCreativeTabsMap.containsKey(pCreativeTab.getTabName()))
		{
			_mCreativeTabsMap.put(pCreativeTab.getTabName(), pCreativeTab);
			return true;
		}
		else
			return false;
	}

	/**
	 * Return CreativeTab instance for given name, if found
	 * @param pDefinedCreativeTabName
	 * @return
	 */
	public CreativeTabs GetCreativeTabInstance(String pDefinedCreativeTabName) {
		if (_mCreativeTabsMap.containsKey(pDefinedCreativeTabName))
			return _mCreativeTabsMap.get(pDefinedCreativeTabName);
		else
			return null;
	}
}
