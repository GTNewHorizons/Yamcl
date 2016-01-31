package eu.usrv.yamcore.persisteddata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.common.io.Files;

import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.IntHelper;
import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.auxiliary.TimeHelper;
import eu.usrv.yamcore.iface.IPersistedDataBase;

/**
 * An attempt to create a persisted storage location for all kinds of values that need to be stored somewhere,
 * without wasting space in the NBT-Files if not necessary
 * @author Namikon
 *
 */
public class PersistedDataBase implements IPersistedDataBase {
	private Map<String, String> _mDataStorage;
	private String _mBufferFileName;
	private File _mConfigBaseDirectory;
	//private long _mLastSave;
	private LogHelper _mLog = YAMCore.instance.getLogger();
	private String _mModCollection = "";
	
	/**
	 * Init persistent storage file. It needs the base directory and a filename 
	 * @param pConfigBaseDirectory
	 * @param pBufferFileName
	 */
	public PersistedDataBase(File pConfigBaseDirectory, String pBufferFileName, String pModCollectionDirectory)
	{
		_mConfigBaseDirectory = pConfigBaseDirectory;
		_mBufferFileName = pBufferFileName;
		_mDataStorage = new HashMap<String, String>();
		_mModCollection = pModCollectionDirectory;
		 
		//_mLastSave = TimeHelper.GetCurrentTimestamp();
	}
	

	@Override
	public void setValue(String pConfigName, String pConfigValue)
	{
		_mLog.debug(String.format("Setting persisted config [%s] to value [%s]", pConfigName, pConfigValue));
		
		_mDataStorage.put(pConfigName, pConfigValue);
		
		//long tCurrent = TimeHelper.GetCurrentTimestamp();
		//if (tCurrent - _mLastSave > 30) // Save every 30 seconds, if changes occour
		//{
			//_mLog.info("Saving local storage file as 30 seconds have passed");
			Save();
		//	_mLastSave = TimeHelper.GetCurrentTimestamp();
		//}
	}
	
	/* (non-Javadoc)
	 * @see com.github.namikon.angermod.persisteddata.IPersistedDataBase#setValue(java.lang.String, int)
	 */
	@Override
	public void setValue(String pConfigName, int pConfigValue)
	{
		setValue(pConfigName, String.format("%d", pConfigValue));
	}
	
	/* (non-Javadoc)
	 * @see com.github.namikon.angermod.persisteddata.IPersistedDataBase#getValueAsInt(java.lang.String, int)
	 */
	@Override
	public int getValueAsInt(String pConfigName, int pDefaultValueOnNull)
	{
		String tCfgValue = getValue(pConfigName);
		if(tCfgValue == null)
			return pDefaultValueOnNull;
		else
			if(IntHelper.tryParse(tCfgValue))
				return Integer.parseInt(tCfgValue);
			else
				return pDefaultValueOnNull;
	}
	
	/* (non-Javadoc)
	 * @see com.github.namikon.angermod.persisteddata.IPersistedDataBase#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String pConfigName)
	{
		return _mDataStorage.get(pConfigName);
	}
	
	// TODO: Move this to some global location!
	private File getStorageFile()
	{
		File tDirectory = new File(_mConfigBaseDirectory, _mModCollection);
		if (!tDirectory.exists())
			tDirectory.mkdir();
		
		File tConfigFileObject = new File(tDirectory, _mBufferFileName);
		return tConfigFileObject;
		//return String.format("%s%s%s%s%s", _mConfigBaseDirectory, File.separator, _mModCollection, File.separator, _mBufferFileName);
	}
	
	/* (non-Javadoc)
	 * @see com.github.namikon.angermod.persisteddata.IPersistedDataBase#Load()
	 */
	@Override
	public boolean Load()
	{
		File tInputFile = getStorageFile();
		boolean tResult = LoadFile(tInputFile);
		
		if (tResult) // Successful load, so we have a working file
		{
			if (tInputFile.exists())
			{
				_mLog.info("Creating backup of currently working storage file...");
				File tBackupFile = new File(tInputFile.getAbsolutePath() + ".backup");
				if (tBackupFile.exists())
					tBackupFile.delete();
				
				try {
					Files.copy(tInputFile, tBackupFile);
				} catch (IOException e) {
					_mLog.error("Unable to create backup of storage file");
					e.printStackTrace();
				}
			}
		}
		
		return tResult;
	}
	
	private boolean LoadFile(File pInputFile)
	{
		boolean tResult = false;
		_mLog.debug("Loading persisted storage file...");
		
		if (!pInputFile.exists())
		{
			_mLog.info("Persisted storage file does not exist. Creating a new one");
			Save();
		}
		
		try {
			InputStream file = new FileInputStream(pInputFile);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			
			try {
				_mDataStorage = (HashMap<String, String>) input.readObject();
				
				tResult = true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally
			{
				input.close();
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return tResult;
	}
	

	/* (non-Javadoc)
	 * @see com.github.namikon.angermod.iface.IPersistedDataBase#Save()
	 */
	@Override
	public void Save()
	{
		File tInputFile = getStorageFile();
		
		try {
			OutputStream file = new FileOutputStream(tInputFile);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			
			try {
				output.writeObject(_mDataStorage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally
			{
				output.close();
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
