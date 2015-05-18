package eu.usrv.yamcore.gameregistry;

import net.minecraft.potion.PotionEffect;

public class PotionHelper {
	/**
	 * Check if a potion has been registered with Forge with this given PotionID
	 * @param pPotionID The ID in question
	 * @return
	 */
	public static boolean IsValidPotionID(int pPotionID)
	{
		try
		{
			// intentional exception.. there must be a better way to do this...
			PotionEffect tEf = new PotionEffect(pPotionID, 1, 0, false);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
