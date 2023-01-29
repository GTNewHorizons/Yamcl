package eu.usrv.yamcore.client.dynamicgui.elements;

import net.minecraft.inventory.Slot;

import eu.usrv.yamcore.client.dynamicgui.helper.TexturedQuadDrawer;
import eu.usrv.yamcore.client.dynamicgui.skins.BackgroundSkin;

public class SlotElement extends SimpleDrawingElement {

    private static final int slotSize = 18;
    protected final Slot slot;

    public SlotElement(Slot slot, BackgroundSkin skin) {
        super(new TexturedQuadDrawer(skin.texture, 0, slotSize, 11, 11 + slotSize));
        this.x = slot.xDisplayPosition - 1;
        this.y = slot.yDisplayPosition - 1;
        drawer.setWH(slotSize, slotSize);
        this.slot = slot;
    }

    public SlotElement(Slot slot) {
        this(slot, BackgroundSkin.defualt);
    }
}
