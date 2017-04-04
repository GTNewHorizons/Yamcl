
package eu.usrv.yamcore.iface;


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

}
