package eu.usrv.yamcore.client.dynamicgui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import eu.usrv.yamcore.client.dynamicgui.helper.NullQuadDrawer;

public class ButtonGuiElement extends SimpleDrawingElement {

    private GuiButton button;

    /**
     * @param buttonId   The id of the button;
     * @param x          The x position of the button;
     * @param y          The y position of the button;
     * @param widthIn    The width of the button;
     * @param heightIn   The height of the button;
     * @param buttonText The text to display on the button;
     * @param enabled    Is the button enabled by default;
     * @param visible    Is the button visible or hidden;
     */
    public ButtonGuiElement(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, boolean enabled,
            boolean visible) {
        super(new NullQuadDrawer());
        button = new GuiButton(buttonId, x, y, widthIn, heightIn, buttonText);
        button.enabled = enabled;
        button.visible = visible;
    }

    @Override
    public void drawBackground(Minecraft mc, int mouseX, int mouseY) {}

    @Override
    public void drawForeground(Minecraft mc, int mouseX, int mouseY) {}

    @Override
    public int getElementWidth() {
        return button.width;
    }

    @Override
    public int getElementHeight() {
        return button.height;
    }

    @Override
    public int getElementX() {
        return button.xPosition;
    }

    @Override
    public int getElementY() {
        return button.yPosition;
    }

    @Override
    public SimpleDrawingElement setXY(int x, int y) {
        button.xPosition = x;
        button.yPosition = y;
        return this;
    }

    public GuiButton getButton() {
        return button;
    }
}
