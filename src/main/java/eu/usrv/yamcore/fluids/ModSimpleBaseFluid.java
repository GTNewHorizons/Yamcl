
package eu.usrv.yamcore.fluids;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eu.usrv.yamcore.YAMCore;


public class ModSimpleBaseFluid extends BlockFluidClassic
{
  public ModSimpleBaseFluid( Fluid fluid, Material material )
  {
    super( fluid, material );
    _mRegisterBucket = true;
    _mPotionEffects = new ArrayList<PotionEffect>();
    _mStackingPotionEffects = new ArrayList<PotionEffect>();
    _mEffectsOnlyToPlayers = false;
    _mRand = new Random( System.currentTimeMillis() );
  }

  private List<PotionEffect> _mPotionEffects;
  private List<PotionEffect> _mStackingPotionEffects;
  private boolean            _mEffectsOnlyToPlayers;
  private Random             _mRand;

  @SideOnly( Side.CLIENT )
  protected IIcon            stillIcon;
  @SideOnly( Side.CLIENT )
  protected IIcon            flowingIcon;

  private String             _mTxtStill;
  private String             _mTxtFlow;
  private String             _mModID;
  private String             _mCreativeTab;

  private boolean            _mRegisterBucket;

  public void setRegisterBucket( boolean pFlag )
  {
    _mRegisterBucket = pFlag;
  }

  public boolean getRegisterBucket()
  {
    return _mRegisterBucket;
  }

  /**
   * Add pEffect to the list of potion effects that will not stack over time
   * 
   * @param pEffect
   */
  public void addPotionEffect( PotionEffect pEffect )
  {
    _mPotionEffects.add( pEffect );
  }

  /**
   * Add pEffect to the list of potion effects that will stack over time as long
   * as the entity remains in the fluid
   * 
   * @param pEffect
   */
  public void addStackingPotionEffect( PotionEffect pEffect )
  {
    _mStackingPotionEffects.add( pEffect );
  }

  /**
   * If set to true, the potion effects will only be applied to Players.
   * If you don't set anything here, ALL entities will get the potion effects
   * 
   * @param pFlag
   */
  public void setApplyEffectsToPlayersOnly( boolean pFlag )
  {
    _mEffectsOnlyToPlayers = pFlag;
  }

  /**
   * Set the textures used to indicate still and liquid/flowing state
   * 
   * @param pModID ModID String, used to locate the resources
   */
  public void SetTextures( String pModID, String pFluidName )
  {
    _mTxtStill = String.format( "%s:fluid%s_still", pModID, pFluidName );
    _mTxtFlow = String.format( "%s:fluid%s_flow", pModID, pFluidName );
  }

  boolean _mCanDisplaceFluids = false;

  /**
   * Define if the fluid is able to replace other fluids in the world. Default is false
   * 
   * @param pFlag
   */
  public void setDisplaceOtherFluids( boolean pFlag )
  {
    _mCanDisplaceFluids = pFlag;
  }

  public void SetModID( String pModID )
  {
    _mModID = pModID;
  }

  @Override
  public IIcon getIcon( int side, int meta )
  {
    return ( side == 0 || side == 1 ) ? stillIcon : flowingIcon;
  }

  @SideOnly( Side.CLIENT )
  @Override
  public void registerBlockIcons( IIconRegister register )
  {
    stillIcon = register.registerIcon( _mTxtStill );
    flowingIcon = register.registerIcon( _mTxtFlow );

    super.getFluid().setIcons( stillIcon, flowingIcon );
  }

  @Override
  public boolean canDisplace( IBlockAccess world, int x, int y, int z )
  {
    if( world.getBlock( x, y, z ).getMaterial().isLiquid() )
      return _mCanDisplaceFluids;
    else
      return super.canDisplace( world, x, y, z );
  }

  @Override
  public boolean displaceIfPossible( World world, int x, int y, int z )
  {
    if( world.getBlock( x, y, z ).getMaterial().isLiquid() )
      return _mCanDisplaceFluids;
    else
      return super.displaceIfPossible( world, x, y, z );
  }

  public void setCreativeTabName( String pTabName )
  {
    _mCreativeTab = pTabName;
  }

  public String getCreativeTabName()
  {
    return _mCreativeTab;
  }

  @Override
  public FluidStack drain( World pWorld, int pX, int pY, int pZ, boolean pDoDrain )
  {
    pWorld.setBlockToAir( pX, pY, pZ );
    return new FluidStack( this.getFluid(), 1000 );
  }

  @Override
  public boolean canDrain( World pWorld, int pX, int pY, int pZ )
  {
    return true;
  }

  /**
   * Is triggered when an entity steps into this fluid
   * 
   * @param pWorld
   * @param pX
   * @param pY
   * @param pZ
   * @param pEntity
   */
  @Override
  public void onEntityCollidedWithBlock( World pWorld, int pX, int pY, int pZ, Entity pEntity )
  {
    super.onEntityCollidedWithBlock( pWorld, pX, pY, pZ, pEntity );

    try
    {
      if( !( pEntity instanceof EntityLivingBase ) )
        return;

      if( !( pEntity instanceof EntityPlayer ) && _mEffectsOnlyToPlayers )
        return;

      EntityLivingBase tElivBase = (EntityLivingBase) pEntity;

      for( PotionEffect tEffect : _mPotionEffects )
        tElivBase.addPotionEffect( tEffect );

      if( _mRand.nextInt( 10 ) == 0 )
      {
        for( PotionEffect tStackingEffect : _mStackingPotionEffects )
        {
          int tDuration = tStackingEffect.getDuration();
          for( Object actEffObj : tElivBase.getActivePotionEffects() )
          {
            PotionEffect pae = (PotionEffect) actEffObj;

            if( pae.getPotionID() == tStackingEffect.getPotionID() )
            {
              tDuration += pae.getDuration();
              break;
            }
          }

          tElivBase.addPotionEffect( new PotionEffect( tStackingEffect.getPotionID(), tDuration, tStackingEffect.getAmplifier() ) );
        }
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
      YAMCore.instance.getLogger().error( String.format( "Apply potioneffect failed for fluidblock %s", this.getFluid().getUnlocalizedName() ) );
    }
  }
}
