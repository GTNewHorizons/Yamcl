package eu.usrv.yamcore.client.dynamicgui.elements;

import eu.usrv.yamcore.client.dynamicgui.helper.IQuadDrawer;
import net.minecraft.client.Minecraft;

public class SimpleDrawingElement implements IGuiElement
{
  
  protected IQuadDrawer drawer;
  protected int x, y;
  protected boolean drawInForeground = false;
  
  public SimpleDrawingElement(IQuadDrawer drawConfig)
  {
    this.drawer = drawConfig;
  }
  
  public SimpleDrawingElement(IQuadDrawer drawConfig, int x, int y, boolean drawForeground)
  {
    this.drawer = drawConfig;
    this.x = x;
    this.y = y;
    drawInForeground = drawForeground;
  }
  
  @Override
  public int getElementHeight()
  {
    return drawer.getHeight();
  }
  
  @Override
  public int getElementWidth()
  {
    return drawer.getWidth();
  }
  
  @Override
  public int getElementY()
  {
    return y;
  }
  
  @Override
  public int getElementX()
  {
    return x;
  }
  
  public SimpleDrawingElement setXY(int x, int y)
  {
    this.x = x;
    this.y = y;
    return this;
  }
  
  @Override
  public void drawBackground(Minecraft mc, int mouseX, int mouseY)
  {
    if (!drawInForeground)
    {
      drawer.draw(x, y);
    }
  }
  
  @Override
  public void drawForeground(Minecraft mc, int mouseX, int mouseY)
  {
    if (drawInForeground)
    {
      drawer.draw(x, y);
    }
  }
  
}
