
package eu.usrv.yamcore.iface;

import net.minecraft.item.Item;

public interface IExtendedItemProperties
{
  String getItemName();
  String getCustomTextureName();
  String getUnlocalizedName();

  /**
   * Return true if this item shall never be consumed in recipes
   * @return
   */
  boolean getDontConsumeInRecipes();
  Item getCustomItem();

}
