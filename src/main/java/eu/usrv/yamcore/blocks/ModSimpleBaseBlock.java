
package eu.usrv.yamcore.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.iface.IExtendedBlockProperties;

public class ModSimpleBaseBlock {

    private final String _mName;
    private String _mTextureOverride;
    private final BlockBase _mBlockInstance;
    private String _mCreativeTab;
    private LogHelper _mLog = YAMCore.instance.getLogger();
    private boolean _mFullyPopulated = false;

    /**
     * Create a new simple block (for recipes or other very basic stuff)
     * 
     * @param pItemName          The Blocks unlocalized name
     * @param pCustomTextureName A custom texture for this block
     * @param pCreativeTabName   The name for the creative tab that will be assigned later in the registration process
     */
    public ModSimpleBaseBlock(Material pBlockBaseMaterial, String pBlockName, String pCustomTextureName,
            String pCreativeTabName) {
        _mName = pBlockName;
        _mCreativeTab = pCreativeTabName;
        _mTextureOverride = pCustomTextureName;

        _mBlockInstance = new BlockBase(pBlockBaseMaterial);
        _mBlockInstance.setBlockName(pBlockName);
    }

    /**
     * Create a new (still simple) Block by using a helper class to provide additional Information about the block's
     * properties
     * 
     * @param pBlockProperties
     */
    public ModSimpleBaseBlock(IExtendedBlockProperties pBlockProperties, String pCreativeTabName) {
        _mName = pBlockProperties.getBlockName();
        _mCreativeTab = pCreativeTabName;
        _mTextureOverride = pBlockProperties.getTextureName();

        _mBlockInstance = new BlockBase(pBlockProperties.getMaterial());
        _mBlockInstance.setBlockName(_mName);

        if (pBlockProperties.getUnbreakable()) _mBlockInstance.setBlockUnbreakable();
        else {
            _mBlockInstance.setHardness(pBlockProperties.getHardness());
            _mBlockInstance.setHarvestLevel(pBlockProperties.getHarvestToolClass(), pBlockProperties.getHarvestLevel());
            _mBlockInstance.setResistance(pBlockProperties.getResistance());
        }
        _mBlockInstance.setStepSound(pBlockProperties.getStepSound());
        _mBlockInstance.setLightLevel(pBlockProperties.getLightLevel());
        _mBlockInstance.setLightOpacity(pBlockProperties.getOpacity());

        _mBlockInstance.setInner_IsOpaque(pBlockProperties.getIsOpaqueCube());
    }

    /**
     * Create a new simple block (for recipes or other very basic stuff)
     * 
     * @param pItemName        The Blocks unlocalized name
     * @param pCreativeTabName The name for the creative tab that will be assigned later in the registration process
     */
    public ModSimpleBaseBlock(Material pBlockBaseMaterial, String pBlockName, String pCreativeTabName) {
        this(pBlockBaseMaterial, pBlockName, "", pCreativeTabName);
    }

    public void setModIDName(String pModID) {

        String tTextureName = "";
        if (_mTextureOverride.length() < 1) tTextureName = String.format("%s:block%s", pModID, _mName);
        else tTextureName = _mTextureOverride;
        // tTextureName = String.format("%s:%s", pModID, _mTextureOverride);

        _mBlockInstance.setBlockTextureName(tTextureName);
        _mFullyPopulated = true;
    }

    public BlockBase getConstructedBlock() {
        if (!_mFullyPopulated) {
            _mLog.error("Block is not fully prepared yet and can't be registered at this point!");
            return null;
        }

        return _mBlockInstance;
    }

    public void setCreativeTab(CreativeTabs pTab) {
        _mBlockInstance.setCreativeTab(pTab);
    }

    public String getCreativeTabName() {
        return _mCreativeTab;
    }

    public String getUnlocItemName() {
        return _mName;
    }
}
