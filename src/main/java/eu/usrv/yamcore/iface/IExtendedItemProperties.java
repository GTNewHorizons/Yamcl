
package eu.usrv.yamcore.iface;

import eu.usrv.yamcore.auxiliary.enums.ItemRecipeBehaviorEnum;

public interface IExtendedItemProperties
{
  String getItemName();
  String getCustomTextureName();
  String getUnlocalizedName();

  /**
   * Define the items behavior on a crafting process.
   * @return
   */
  ItemRecipeBehaviorEnum getItemRecipeBehavior();
}
