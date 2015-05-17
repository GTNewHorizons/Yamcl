package eu.usrv.yamcore.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class ModSimpleBaseFluid extends BlockFluidClassic {
	public ModSimpleBaseFluid(Fluid fluid, Material material) {
		super(fluid, material);
	}
	
	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;
      
	private String _mTxtStill;
	private String _mTxtFlow;
	private String _mModID;
	private String _mCreativeTab;
	
	/** 
	 * Set the textures used to indicate still and liquid/flowing state
	 * @param pModID ModID String, used to locate the resources
	 * @param pTextureStill Texture for still fluid
	 * @param pTextureFlowing Texture for flowing fluid
	 */
	public void SetTextures(String pModID, String pTextureStill, String pTextureFlowing)
	{
		_mTxtStill = pTextureStill;
		_mTxtFlow = pTextureFlowing;
	}
	boolean _mCanDisplaceFluids = false;
	
	/**
	 * Define if the fluid is able to replace other fluids in the world. Default is false
	 * @param pFlag
	 */
	public void setDisplaceOtherFluids(boolean pFlag)
	{
		_mCanDisplaceFluids = pFlag;
	}
	
	public void SetModID(String pModID)
	{
		_mModID = pModID;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
    	return (side == 0 || side == 1)? stillIcon : flowingIcon;
	}
     
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		stillIcon = register.registerIcon(_mTxtStill);
		flowingIcon = register.registerIcon(_mTxtFlow);
	}
      
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x,  y,  z).getMaterial().isLiquid())
			return _mCanDisplaceFluids;
		else
			return super.canDisplace(world, x, y, z);
	}
      
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x,  y,  z).getMaterial().isLiquid())
			return _mCanDisplaceFluids;
		else
			return super.displaceIfPossible(world, x, y, z);
	}
	
	public String getCreativeTabName()
	{
		return _mCreativeTab;
	}
}
