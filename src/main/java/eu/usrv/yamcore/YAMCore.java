
package eu.usrv.yamcore;


import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.events.BucketHandler;
import eu.usrv.yamcore.proxy.CommonProxy;


/**
 * @author Namikon
 * 
 */
@Mod( modid = "YAMCore", name = "YAMCore", version = "GRADLETOKEN_VERSION" )
public class YAMCore
{
  private LogHelper _mLogger = new LogHelper( "Yamcl" );

  public LogHelper getLogger()
  {
    return _mLogger;
  }

  @SidedProxy( clientSide = "eu.usrv.yamcore.proxy.ClientProxy", serverSide = "eu.usrv.yamcore.proxy.CommonProxy" )
  public static CommonProxy proxy;

  @Instance( "YAMCore" )
  public static YAMCore     instance = new YAMCore();

  private boolean DebugTagFileFound( FMLPreInitializationEvent event )
  {
    File tFile = new File( event.getModConfigurationDirectory() + "/YAMCoreDebug" );
    return tFile.exists();
  }

  @EventHandler
  public void preInit( FMLPreInitializationEvent event )
  {
    if( DebugTagFileFound( event ) )
    {
      _mLogger.info( "YAMCore debug information ENABLED" );
      _mLogger.setDebugOutput( true );
    }

    MinecraftForge.EVENT_BUS.register( BucketHandler.INSTANCE );
  }

  @EventHandler
  public void Init( FMLInitializationEvent event )
  {
  }

  @EventHandler
  public void postInit( FMLPostInitializationEvent event )
  {

  }
}
