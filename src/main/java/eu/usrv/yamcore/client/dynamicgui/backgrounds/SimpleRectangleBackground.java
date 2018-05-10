package eu.usrv.yamcore.client.dynamicgui.backgrounds;

import eu.usrv.yamcore.client.dynamicgui.elements.SimpleDrawingElement;
import eu.usrv.yamcore.client.dynamicgui.helper.IQuadDrawer;
import eu.usrv.yamcore.client.dynamicgui.helper.TexturedQuadDrawer;
import eu.usrv.yamcore.client.dynamicgui.skins.BackgroundSkin;
import net.minecraft.util.ResourceLocation;

public class SimpleRectangleBackground extends GUIBackgroundProvider
{
  public SimpleRectangleBackground(BackgroundSkin skin, int width, int height)
  {
    super(skin, width, height);
  }
  
  public SimpleRectangleBackground(int width, int height)
  {
    super(width, height);
  }
  
  @Override
  protected void addComponents()
  {
    ResourceLocation tex = skin.texture;
    components.add(new SimpleDrawingElement(BackgroundConfig.FILLER.getConfig(tex)
            .setWH(width - 5, height - 5)).setXY(2, 2));
    
    components.add(new SimpleDrawingElement(BackgroundConfig.SIDE_TOP.getConfig(tex)
            .setWH(width - 5, 3)).setXY(2, 0));
    components.add(new SimpleDrawingElement(BackgroundConfig.SIDE_BOTTOM.getConfig(tex)
            .setWH(width - 5, 3)).setXY(2, height - 3));
    components.add(new SimpleDrawingElement(BackgroundConfig.SIDE_LEFT.getConfig(tex)
            .setWH(3, height - 5)).setXY(0, 2));
    components.add(new SimpleDrawingElement(BackgroundConfig.SIDE_RIGHT.getConfig(tex)
            .setWH(3, height - 4)).setXY(width - 3, 2));
    
    components.add(new SimpleDrawingElement(BackgroundConfig.CORNER_TOP_LEFT.getConfig(tex)
            .setWH(3, 3)).setXY(0, 0));
    components.add(new SimpleDrawingElement(BackgroundConfig.CORNER_TOP_RIGHT.getConfig(tex)
            .setWH(4, 4)).setXY(width - 4, 0));
    components.add(new SimpleDrawingElement(BackgroundConfig.CORNER_BOTTOM_LEFT.getConfig(tex)
            .setWH(3, 3)).setXY(0, height - 3));
    components.add(new SimpleDrawingElement(BackgroundConfig.CORNER_BOTTOM_RIGHT.getConfig(tex)
            .setWH(4, 4)).setXY(width - 4, height - 4));
  }
  
  enum BackgroundConfig
  {
    FILLER(5, 6, 5, 6),
    SIDE_TOP(5, 6, 0, 3),
    SIDE_BOTTOM(5, 6, 8, 11),
    SIDE_LEFT(0, 3, 5, 6),
    SIDE_RIGHT(8, 11, 5, 6),
    CORNER_TOP_LEFT(0, 3, 0, 3),
    CORNER_TOP_RIGHT(7, 11, 0, 4),
    CORNER_BOTTOM_LEFT(0, 3, 7, 10),
    CORNER_BOTTOM_RIGHT(7, 11, 7, 11);
    public final int uLow, uHigh, vLow, vHigh;
    
    BackgroundConfig(int uLow, int uHigh, int vLow, int vHigh)
    {
      this.uLow = uLow;
      this.uHigh = uHigh;
      this.vLow = vLow;
      this.vHigh = vHigh;
    }
    
    public IQuadDrawer getConfig(ResourceLocation tex)
    {
      return new TexturedQuadDrawer(tex, uLow, uHigh, vLow, vHigh);
    }
  }
  
}