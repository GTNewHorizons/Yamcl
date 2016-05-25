
package eu.usrv.yamcore.iface;


import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;


public interface IExtendedBlockProperties
{
  public Material getMaterial();

  /**
   * Unbreakable sand anyone?
   * 
   * @return
   */
  public boolean getUnbreakable();

  /**
   * The block's harvest level. Vanilla values are: 0: wood 3: diamond
   * 
   * @return
   */
  public int getHarvestLevel();

  /**
   * The higher this number is, the more light is beeing blocked by this block
   * 
   * @return
   */
  public int getOpacity();

  /**
   * Can be ignored if getHarvestLevel() will return true
   * 
   * @return
   */
  public float getHardness();

  /**
   * How much light will this block emit. 0.0F = nothing, 1.0F = daylight
   * 
   * @return
   */
  public float getLightLevel();

  /**
   * Can be ignored if getHarvestLevel() will return true
   * 
   * @return
   */
  public float getResistance();

  /**
   * The Blockname that is used to register this block
   * 
   * @return
   */
  public String getBlockName();

  /**
   * The custom textureName. Return an empty string to use default texture mapping
   * 
   * @return
   */
  public String getTextureName();

  /**
   * Can be ignored if getHarvestLevel() will return true
   * 
   * @return
   */
  public String getHarvestToolClass();

  /**
   * The sound this block will make if any entity walks over it
   * 
   * @return
   */
  public SoundType getStepSound();

  /**
   * Set to false if the Block-texture has an alpha channel
   * See here for more information: http://greyminecraftcoder.blogspot.de/2013/07/rendering-transparent-blocks.html
   * 
   * @return
   */
  public boolean getIsOpaqueCube();
}
