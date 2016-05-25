
package eu.usrv.yamcore.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class BlockBase extends Block
{
  private boolean _mIsOpaqueCube;

  public BlockBase( Material pMaterial )
  {
    super( pMaterial );
    _mIsOpaqueCube = true;
  }

  public void setInner_IsOpaque( boolean pFlag )
  {
    _mIsOpaqueCube = pFlag;
  }

  @Override
  public boolean isOpaqueCube()
  {
    return _mIsOpaqueCube;
  }
}
