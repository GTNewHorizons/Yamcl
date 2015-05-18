package eu.usrv.yamcore.gameregistry;

import net.minecraft.util.DamageSource;

public class DamageTypeHelper {
	/**
	 * Check if given DamageSource by string is a valid vanilla damage source
	 * @param pDamageSource
	 * @return
	 */
	public static boolean IsValidDamageSource(String pDamageSource)
	{
		return ParseStringToDamageSource(pDamageSource) == null ? false : true;
	}
	
	/**
	 * Parse damagesource from a string into a valid vanilla damage source
	 * @param pDamageSource
	 * @return
	 */
	public static DamageSource ParseStringToDamageSource(String pDamageSource)
	{
		// I used switch, but 1.6 compat mode does not like this :(
		DamageSource tSrc = null;
		if (pDamageSource.equalsIgnoreCase("inFire"))
			tSrc = DamageSource.inFire;
		if (pDamageSource.equalsIgnoreCase("onFire"))
			tSrc = DamageSource.onFire;
		if (pDamageSource.equalsIgnoreCase("lava"))
			tSrc = DamageSource.lava;
		if (pDamageSource.equalsIgnoreCase("inWall"))
			tSrc = DamageSource.inFire;
		if (pDamageSource.equalsIgnoreCase("drown"))
			tSrc = DamageSource.drown;
		if (pDamageSource.equalsIgnoreCase("starve"))
			tSrc = DamageSource.starve;
		if (pDamageSource.equalsIgnoreCase("cactus"))
			tSrc = DamageSource.cactus;
		if (pDamageSource.equalsIgnoreCase("fall"))
			tSrc = DamageSource.fall;
		if (pDamageSource.equalsIgnoreCase("outOfWorld"))
			tSrc = DamageSource.outOfWorld;
		if (pDamageSource.equalsIgnoreCase("generic"))
			tSrc = DamageSource.generic;
		if (pDamageSource.equalsIgnoreCase("magic"))
			tSrc = DamageSource.magic;
		if (pDamageSource.equalsIgnoreCase("wither"))
			tSrc = DamageSource.wither;
		if (pDamageSource.equalsIgnoreCase("anvil"))
			tSrc = DamageSource.anvil;
		if (pDamageSource.equalsIgnoreCase("fallingBlock"))
			tSrc = DamageSource.fallingBlock;
	
		return tSrc;
	}
}
