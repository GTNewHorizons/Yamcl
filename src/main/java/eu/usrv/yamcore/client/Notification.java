
package eu.usrv.yamcore.client;

import java.util.Objects;

import net.minecraft.item.ItemStack;

/**
 * Original source copied from BeyondRealityCore. All credits go to pauljoda for this code
 *
 * @author pauljoda
 */
public class Notification {

    private final ItemStack icon;
    private final String title;
    private final String description;

    public Notification(ItemStack stack, String title, String desc) {
        icon = stack;
        this.title = title;
        description = desc;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return title.equals(that.title) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
