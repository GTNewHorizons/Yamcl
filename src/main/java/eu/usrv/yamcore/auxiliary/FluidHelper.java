
package eu.usrv.yamcore.auxiliary;


import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;


public class FluidHelper
{
  /**
   * Try to find the fluid that is in the fluidcontainer. Returns null if none
   * could be found
   * 
   * @param pItemStack
   * @return
   */
  public static Fluid getFluidFromContainer( ItemStack pItemStack )
  {
    Fluid tRet = null;
    if( pItemStack != null )
    {
      FluidStack fs = getFluidStackFromContainer( pItemStack );
      if( fs != null )
        tRet = fs.getFluid();
    }

    return tRet;
  }

  /**
   * Get the fluidStack of given itemStack, if there is any.
   * returns null if itemstack is null or not a fluid container
   * 
   * @param pItemStack
   * @return
   */
  public static FluidStack getFluidStackFromContainer( ItemStack pItemStack )
  {
    FluidStack tReturnVal = null;

    if( (Object) pItemStack.getItem() instanceof IFluidContainerItem )
    {
      IFluidContainerItem tFluidContainer = IFluidContainerItem.class.cast( pItemStack.getItem() );
      FluidStack tContents = tFluidContainer.getFluid( pItemStack );
      if( tContents != null )
      {
        tReturnVal = tContents;
      }
    }

    return tReturnVal;
  }
}
