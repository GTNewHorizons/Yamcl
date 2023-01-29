
package eu.usrv.yamcore.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eu.usrv.yamcore.iface.IModItem;

public class ModCreativeTab extends CreativeTabs {

    private String _mTabName;
    private Item _mIconItem;

    /**
     * Create new CreativeTab with vanilla item as icon
     * 
     * @param pTabName
     * @param pTabIcon
     */
    public ModCreativeTab(String pTabName, Item pTabIcon) {
        super(CreativeTabs.getNextID(), pTabName);
        _mTabName = pTabName;
        _mIconItem = pTabIcon;
    }

    /**
     * Create new CreativeTab with custom item as icon
     * 
     * @param pTabName
     * @param pTabIcon
     */
    public ModCreativeTab(String pTabName, IModItem pTabIcon) {
        this(pTabName, pTabIcon.getConstructedItem());
    }

    public String getTabName() {
        return _mTabName;
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        if (_mIconItem == null) return Items.iron_axe;
        else return _mIconItem;
    }
}
