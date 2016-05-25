
package eu.usrv.yamcore.proxy;


import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;


public class CommonProxy
{

  /**
   * Returns a side-appropriate EntityPlayer for use during message handling
   */
  public EntityPlayer getPlayerEntity( MessageContext ctx )
  {
    return ctx.getServerHandler().playerEntity;
  }
}
