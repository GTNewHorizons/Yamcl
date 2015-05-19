package eu.usrv.yamcore;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import eu.usrv.yamcore.auxiliary.*;
import eu.usrv.yamcore.events.BucketHandler;
import eu.usrv.yamcore.iface.IPersistedDataBase;
import eu.usrv.yamcore.persisteddata.PersistedDataBase;


/**
 * @author Namikon
 *
 */
@Mod(modid = "YAMCore", name = "YAMCore", version = "0.3")
public class YAMCore {
	private LogHelper _mLogger = new LogHelper("Yamcl");
	
	public LogHelper getLogger()
	{
		return _mLogger;
	}
	
	@Instance("YAMCore")
	public static YAMCore instance = new YAMCore();
	
	private boolean DebugTagFileFound(FMLPreInitializationEvent event)
	{
		File tFile = new File(event.getModConfigurationDirectory() + "/YAMCoreDebug");
		return tFile.exists();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		if(DebugTagFileFound(event))
		{
			_mLogger.info("YAMCore debug information ENABLED");
			_mLogger.setDebugOutput(true);
		}
		
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	
	}
}
