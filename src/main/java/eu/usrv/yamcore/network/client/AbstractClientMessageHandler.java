
package eu.usrv.yamcore.network.client;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import eu.usrv.yamcore.network.AbstractMessageHandler;

/**
 * 
 * This is just a convenience class that will prevent the server-side message handling method from appearing in our
 * client message handler classes.
 * 
 */
public abstract class AbstractClientMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {

    // implementing a final version of the server message handler both prevents it from
    // appearing automatically and prevents us from ever accidentally overriding it
    public final IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx) {
        return null;
    }
}
