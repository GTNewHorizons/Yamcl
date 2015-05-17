package eu.usrv.yamcore.auxiliary;

import eu.usrv.yamcore.YAMCore;
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
			YAMCore.instance.getLogger().error(String.format("Error while looking for EntityPlayer with Name %s", pName));
			YAMCore.instance.getLogger().DumpStack(e);
		}
		return tEP;
	}
}
