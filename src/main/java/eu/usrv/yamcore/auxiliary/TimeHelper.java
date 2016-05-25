
package eu.usrv.yamcore.auxiliary;


import java.util.Date;

import eu.usrv.yamcore.YAMCore;


/**
 * Generic functions about time
 * 
 * @author Namikon
 * 
 */
public class TimeHelper
{
  public static long GetCurrentTimestamp()
  {
    try
    {
      Date now = new Date();
      return (long) ( now.getTime() / 1000 );
    }
    catch( Exception e )
    {
      YAMCore.instance.getLogger().error( "TimeHelper.GetCurrentTimestamp.Exception", "GetCurrentTimestamp failed and is probably unreliable. You totally should report this..." );
      YAMCore.instance.getLogger().DumpStack( "TimeHelper.GetCurrentTimestamp.Exception.Stack", e );
      return 0;
    }
  }
}
