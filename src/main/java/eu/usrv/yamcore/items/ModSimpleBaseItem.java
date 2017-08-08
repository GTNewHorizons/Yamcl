
package eu.usrv.yamcore.items;


import eu.usrv.yamcore.auxiliary.enums.ItemRecipeBehaviorEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.iface.IExtendedItemProperties;


public class ModSimpleBaseItem
{
  private final String _mName;
  private String _mTextureOverride;
  private final ItemBase _mItemInstance;
  private String _mCreativeTab;
  private LogHelper _mLog = YAMCore.instance.getLogger();
  private boolean _mFullyPopulated = false;

  /**
   * Create a new simple item (for recipes or other very basic stuff)
   *
   * @param pItemName          The Item's unlocalized name
   * @param pCustomTextureName A custom texture for this item
   * @param pCreativeTabName   The name for the creative tab that will be assigned later in the registration process
   */
  public ModSimpleBaseItem( String pItemName, String pCustomTextureName, String pCreativeTabName )
  {
    _mName = pItemName;
    _mCreativeTab = pCreativeTabName;
    _mTextureOverride = pCustomTextureName;

    _mItemInstance = new ItemBase();
    _mItemInstance.setUnlocalizedName( _mName );

    // _mLog.info(String.format("New item. Name: %s CreativeTabName: %s Texture: %s", pItemName, pCreativeTabName,
    // pCustomTextureName));
  }

  public ModSimpleBaseItem( IExtendedItemProperties pItemProperties, String pCreativeTabName )
  {
    _mName = pItemProperties.getItemName();
    _mCreativeTab = pCreativeTabName;
    _mTextureOverride = pItemProperties.getCustomTextureName();

    _mItemInstance = new ItemBase( pItemProperties.getItemRecipeBehavior() );
    _mItemInstance.setUnlocalizedName( pItemProperties.getUnlocalizedName() );
    if( pItemProperties.getItemRecipeBehavior() == ItemRecipeBehaviorEnum.NoConsume || pItemProperties.getItemRecipeBehavior() == ItemRecipeBehaviorEnum.NoConsumeLeaveInGrid )
      _mItemInstance.setContainerItem( _mItemInstance );
  }

  /**
   * Create a new simple item (for recipes or other very basic stuff). The texturename will be [modid]:item[pItemName]
   *
   * @param pItemName        The Item's unlocalized name
   * @param pCreativeTabName The name for the creative tab that will be assigned later in the registration process
   */
  public ModSimpleBaseItem( String pItemName, String pCreativeTabName )
  {
    this( pItemName, "", pCreativeTabName );
  }

  public void setModIDName( String pModID )
  {

    String tTextureName = "";
    if( _mTextureOverride.length() < 1 )
      tTextureName = String.format( "%s:item%s", pModID, _mName );
    else
      tTextureName = String.format( "%s:%s", pModID, _mTextureOverride );

    _mItemInstance.setTextureName( tTextureName );
    _mFullyPopulated = true;
  }

  public Item getConstructedItem()
  {
    if( !_mFullyPopulated )
    {
      _mLog.error( "Item is not fully prepared yet and can't be registered at this point!" );
      return null;
    }

    return _mItemInstance;
  }

  public void setCreativeTab( CreativeTabs pTab )
  {
    _mItemInstance.setCreativeTab( pTab );
  }

  public String getCreativeTabName()
  {
    return _mCreativeTab;
  }

  public String getUnlocItemName()
  {
    return _mName;
  }
}
