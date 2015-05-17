package eu.usrv.yamcl.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public abstract class ModBaseItem {
	private final String _mName;
	private final String _mTextureName;
	private final Item _mItemInstance;
	private String _mCreativeTab;
	
	public ModBaseItem(String pModID, String pItemName, String pTextureName, String pCreativeTabName)
	{
		_mName = pItemName;
		if (pTextureName.length() == 0)
			_mTextureName = String.format("%s:item%s", pModID, pItemName);
		else
			_mTextureName = String.format("%s:%s", pModID, pTextureName);
		
		_mCreativeTab = pCreativeTabName;
		
		_mItemInstance = new Item();
		//tItem.setCreativeTab(modItem.getCreativeTab()); // we need to set that later
		_mItemInstance.setUnlocalizedName(_mName);
		_mItemInstance.setTextureName(_mTextureName);
	}
	
	public Item getConstructedItem()
	{
		return _mItemInstance;
	}
	
	public void setCreativeTab(CreativeTabs pTab)
	{
		_mItemInstance.setCreativeTab(pTab);
	}
	
	public String getCreativeTabName()
	{
		return _mCreativeTab;
	}
	
	public String getTextureName()
	{
		return _mTextureName;
	}
	
	public String getUnlocItemName()
	{
		return _mName;
	}
}
