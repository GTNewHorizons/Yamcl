package eu.usrv.yamcl.auxiliary;

import eu.usrv.yamcl.Yamcl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

/**
 * Generic World-Helper
 * @author Namikon
 *
 */
public class WorldHelper {
	/**
	 * Find EntityPlayer instance for a given Minecraft name
	 * @param pName
	 * @return
	 */
	public static EntityPlayer FindPlayerByName(String pName) {
		EntityPlayer tEP = null;
		try
		{
			for (World world : DimensionManager.getWorlds())
			{
				tEP = world.getPlayerEntityByName(pName);
				if (tEP != null)
					break;
			}
			  
		}
		catch (Exception e)
		{
			Yamcl.instance.getLogger().error(String.format("Error while looking for EntityPlayer with Name %s", pName));
			Yamcl.instance.getLogger().DumpStack(e);
		}
		return tEP;
	}
}
