package eu.usrv.yamcl.auxiliary;

import java.util.Date;

import eu.usrv.yamcl.Yamcl;

/**
 * Generic functions about time
 * @author Namikon
 *
 */
public class TimeHelper {
	public static long GetCurrentTimestamp()
	{
		try
		{
			Date now = new Date();
			return (long)(now.getTime() / 1000);		
		} catch (Exception e)
		{
			Yamcl.instance.getLogger().error("TimeHelper.GetCurrentTimestamp.Exception", "GetCurrentTimestamp failed and is probably unreliable. You totally should report this...");
			Yamcl.instance.getLogger().DumpStack("TimeHelper.GetCurrentTimestamp.Exception.Stack", e);
			return 0;
		}
	}
}
