package eu.usrv.yamcore.client.dynamicgui.elements;

import eu.usrv.yamcore.client.dynamicgui.helper.ColoredQuadDrawer;
import eu.usrv.yamcore.client.dynamicgui.helper.IQuadDrawer;
import eu.usrv.yamcore.client.dynamicgui.widgets.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.opengl.GL11;

public class DisplayElement implements IWidget
{
  private static final int padding = 3;
  private final int[] colors;
  private final String[] lines;
  private final GuiContainer gui;
  private final FontRenderer font;
  private final IQuadDrawer boxWhite, boxBlack;
  protected int x, y, width, height;
  protected IDisplayElementDataProvider dataProvider;
  
  public DisplayElement(GuiContainer gui, FontRenderer font, int x, int y, int width, int height)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.gui = gui;
    this.font = font;
    int lineCount = height / (font.FONT_HEIGHT + 1);
    colors = new int[lineCount];
    lines = new String[lineCount];
    for (int i = 0; i < lineCount; i++)
    {
      colors[i] = 0xFFFFFF;
      lines[i] = "";
    }
    boxWhite = new ColoredQuadDrawer(0xFFFFFF, width, height);
    boxBlack = new ColoredQuadDrawer(0x000000, width - 2, height - 2);
    
  }
  
  @Override
  public void drawBackground(Minecraft mc, int mouseX, int mouseY)
  {
    boxWhite.draw(x, y);
    boxBlack.draw(x + 1, y + 1);
    GL11.glColor4f(1f, 1f, 1f, 1f);
  }
  
  @Override
  public void drawForeground(Minecraft mc, int mouseX, int mouseY)
  {
    GL11.glTranslatef(x, y, 0f);
    GL11.glScalef(.5f, .5f, 0f);
    for (int i = 0; i < lines.length; i++)
    {
      drawString(i);
    }
    GL11.glScalef(2f, 2f, 0f);
    GL11.glTranslatef(-x, -y, 0f);
  }
  
  public void drawString(int lineNum)
  {
    gui.drawString(font, lines[lineNum], padding, padding + font.FONT_HEIGHT * lineNum, colors[lineNum]);
  }
  
  @Override
  public int getElementHeight()
  {
    return width;
  }
  
  @Override
  public int getElementWidth()
  {
    return height;
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
  
  @Override
  public void update()
  {
  }
}