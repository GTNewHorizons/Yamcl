
package eu.usrv.yamcore.client;


import net.minecraft.item.ItemStack;


/**
 * Original source copied from BeyondRealityCore. All credits go to pauljoda for this code
 * 
 * @author pauljoda
 * 
 */
public class Notification
{
  private ItemStack icon;
  private String    title;
  private String    description;

  public Notification( ItemStack stack, String t, String d )
  {
    icon = stack;
    title = t;
    description = d;
  }

  public ItemStack getIcon()
  {
    return icon;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }
}