
package eu.usrv.yamcore.world;


import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;


/**
 * A simple Position-Helper to provide a convenient way to work with coordinates
 * Inspired by RogueLike Dungeons https://github.com/Greymerk/minecraft-roguelike
 */
public class EzCoords
{
  private int _mX;
  private int _mY;
  private int _mZ;

  public EzCoords( int pX, int pY, int pZ )
  {
    _mX = pX;
    _mY = pY;
    _mZ = pZ;
  }

  public EzCoords( EzCoords pToCopy )
  {
    _mX = pToCopy.getX();
    _mY = pToCopy.getY();
    _mZ = pToCopy.getZ();
  }

  public EzCoords( Vec3 pPosition )
  {
    _mX = (int) pPosition.xCoord;
    _mY = (int) pPosition.yCoord;
    _mZ = (int) pPosition.zCoord;
  }

  public int getX()
  {
    return _mX;
  }

  public int getY()
  {
    return _mY;
  }

  public int getZ()
  {
    return _mZ;
  }

  public void add( ForgeDirection pDirection, int pAmount )
  {

    switch( pDirection )
    {
      case EAST:
        _mX += pAmount;
        return;
      case WEST:
        _mX -= pAmount;
        return;
      case UP:
        _mY += pAmount;
        return;
      case DOWN:
        _mY -= pAmount;
        return;
      case NORTH:
        _mZ -= pAmount;
        return;
      case SOUTH:
        _mZ += pAmount;
        return;
      case UNKNOWN:
        break;
      default:
        break;
    }
  }

  public EzCoords add( EzCoords other )
  {
    _mX += other.getX();
    _mY += other.getY();
    _mZ += other.getZ();
    return this;
  }

  public EzCoords sub( EzCoords other )
  {
    _mX -= other.getX();
    _mY -= other.getY();
    _mZ -= other.getZ();
    return this;
  }

  public Vec3 getVec3()
  {
    return Vec3.createVectorHelper( _mX, _mY, _mZ );
  }

  public void add( ForgeDirection pDirection )
  {
    add( pDirection, 1 );
  }

  public double distance( EzCoords pOther )
  {
    double side1 = Math.abs( this.getX() - pOther.getX() );
    double side2 = Math.abs( this.getZ() - pOther.getZ() );

    return Math.sqrt( ( side1 * side1 ) + ( side2 * side2 ) );
  }

  @Override
  public int hashCode()
  {
    return new HashCodeBuilder( 17, 31 ).append( _mX ).append( _mY ).append( _mZ ).toHashCode();
  }

  @Override
  public String toString()
  {
    return String.format( "X: %d Y: %d Z: %d", _mX, _mY, _mZ );
  }

  @Override
  public boolean equals( Object pOther )
  {
    if( pOther == null )
      return false;

    if( pOther instanceof EzCoords )
    {
      EzCoords other = (EzCoords) pOther;

      if( _mX != other.getX() )
        return false;
      if( _mY != other.getY() )
        return false;
      if( _mZ != other.getZ() )
        return false;

      return true;
    }
    else
      return false;
  }
}
