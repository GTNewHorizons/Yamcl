
package eu.usrv.yamcore.auxiliary.classes;

import net.minecraft.nbt.NBTTagCompound;

import com.google.gson.JsonObject;

import eu.usrv.yamcore.auxiliary.ItemDescriptor;

public class JSONHoverEvent {

    public enum EventTypeEnum {
        SHOW_TEXT,
        SHOW_ITEM,
        SHOW_ACHIEVEMENT,
        SHOW_ENTITY
    }

    public EventTypeEnum EventType;
    public String Text;

    /**
     * Generate a Hover-Event that will display regular text.
     * 
     * @param pMessage The message to be displayed as tooltip
     * @return
     */
    public static JSONHoverEvent SimpleText(String pMessage) {
        return new JSONHoverEvent(EventTypeEnum.SHOW_TEXT, pMessage, null);
    }

    /**
     * Generate a Hover-Event that will display an Item tooltip. This will also show any lore this item contains
     * 
     * @param pItem The itemdescriptor of that item
     * @return
     */
    public static JSONHoverEvent Item(ItemDescriptor pItem) {
        return new JSONHoverEvent(EventTypeEnum.SHOW_ITEM, "", pItem);
    }

    /**
     * Generate a Hover-Event that will show an achievement. Take the full name from your own mod or check
     * /stats/uuid.json for the achievement you want to display
     * 
     * @param pStatIdentifier
     * @return
     */
    public static JSONHoverEvent Achievement(String pStatIdentifier) {
        return new JSONHoverEvent(EventTypeEnum.SHOW_ACHIEVEMENT, pStatIdentifier, null);
    }

    // public static JSONHoverEvent Entity( String pMessage )
    // {
    // return new JSONHoverEvent( EventTypeEnum.SHOW_ENTITY, pMessage, null );
    // }

    private JSONHoverEvent(EventTypeEnum pEventType, String pText, ItemDescriptor pDescriptor) {
        EventType = pEventType;
        if (EventType == EventTypeEnum.SHOW_ITEM) {
            NBTTagCompound tItem = new NBTTagCompound();
            pDescriptor.getItemStack(1).writeToNBT(tItem);
            Text = tItem.toString();
        } else Text = pText;

    }

    /**
     * Return the constructed JSON Element. Use it in your own chat-handler, or pass it to JSONChatText class
     * 
     * @return
     */
    public JsonObject getJson() {
        JsonObject tJO = new JsonObject();
        tJO.addProperty("action", EventType.toString().toLowerCase());
        tJO.addProperty("value", Text);
        return tJO;
    }
}
