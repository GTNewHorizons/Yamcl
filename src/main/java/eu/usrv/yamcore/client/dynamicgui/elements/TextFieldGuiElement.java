package eu.usrv.yamcore.client.dynamicgui.elements;

import eu.usrv.yamcore.client.dynamicgui.helper.NullQuadDrawer;
import eu.usrv.yamcore.client.dynamicgui.widgets.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class TextFieldGuiElement extends SimpleDrawingElement implements IWidget
{
  int staticX, staticY;
  GuiTextField textField;
  
  public TextFieldGuiElement(int id, FontRenderer fontRendererObj, int x, int y, int width, int height)
  {
    super(new NullQuadDrawer());
    textField = new GuiTextField(fontRendererObj, x, y, width, height);
    staticX = x;
    staticY = y;
  }
  
  @Override
  public void drawBackground(Minecraft mc, int mouseX, int mouseY) {textField.drawTextBox();}
  
  @Override
  public void drawForeground(Minecraft mc, int mouseX, int mouseY) {}
  
  @Override
  public int getElementHeight()
  {
    return textField.height;
  }
  
  @Override
  public int getElementWidth()
  {
    return textField.width;
  }
  
  @Override
  public int getElementX()
  {
    return staticX;
  }
  
  @Override
  public int getElementY()
  {
    return staticY;
  }
  
  @Override
  public void update()
  {
    textField.updateCursorCounter();
  }
  
  @Override
  public SimpleDrawingElement setXY(int x, int y)
  {
    textField.xPosition = x;
    textField.yPosition = y;
    return this;
  }
  
  public GuiTextField getTextField()
  {
    return textField;
  }
  
  public void mouseClicked(int mouseX, int mouseY, int mouseButton)
  {
    if (mouseX > textField.xPosition && mouseX < textField.xPosition + getElementWidth() && mouseY > textField.yPosition && mouseY < textField.yPosition + getElementY())
    {
      if (mouseButton == 1)
      {
        textField.setText("");
      }
      textField.mouseClicked(mouseX, mouseY, mouseButton);
    }
  }
}