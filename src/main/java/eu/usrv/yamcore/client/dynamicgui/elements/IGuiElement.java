package eu.usrv.yamcore.client.dynamicgui.elements;

import net.minecraft.client.Minecraft;

public interface IGuiElement {

    void drawBackground(Minecraft mc, int mouseX, int mouseY);

    void drawForeground(Minecraft mc, int mouseX, int mouseY);

    int getElementHeight();

    int getElementWidth();

    int getElementX();

    int getElementY();
}
