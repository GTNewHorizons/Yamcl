package eu.usrv.yamcore.network;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import eu.usrv.yamcore.network.client.AbstractClientMessageHandler;

// Thanks coolAlias; I owe you a beer
// Based on:
// http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/2137055-1-7-x-1-8-customizing-packet-handling-with
public abstract class PacketDispatcher
{
    // a simple counter will allow us to get rid of 'magic' numbers used during packet registration
    private byte _mPacketId = 0;
    
    public PacketDispatcher(String pModID)
    {
    	dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(pModID);
    }
    
    private final SimpleNetworkWrapper dispatcher;
    
    /**
     * Call this during pre-init or loading and register all of your packets (messages) here
     */
    public abstract void registerPackets();

    /**
     * Register a message; Automatic side detection
     * @param handlerClass
     * @param messageClass
     */
    protected final <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> handlerClass, Class<REQ> messageClass) {
        Side side = AbstractClientMessageHandler.class.isAssignableFrom(handlerClass) ? Side.CLIENT : Side.SERVER;
        dispatcher.registerMessage(handlerClass, messageClass, _mPacketId++, side);
    }
    

    /**
     * Register a message directly which is meant for a specific side
     * @param pHandlerClass
     * @param pMessageClass
     * @param pSide
     */
    protected final void registerMessage(Class pHandlerClass, Class pMessageClass, Side pSide) {
        dispatcher.registerMessage(pHandlerClass, pMessageClass, _mPacketId++, pSide);
    }

    /**
    * Send this message to the specified player.
    * See {@link SimpleNetworkWrapper#sendTo(IMessage, EntityPlayerMP)}
    */
    public final void sendTo(IMessage pMessage, EntityPlayerMP pPlayer)
    {
        dispatcher.sendTo(pMessage, pPlayer);
    }

    /**
    * Send this message to everyone within a certain range of a point.
    * See {@link SimpleNetworkWrapper#sendToDimension(IMessage, NetworkRegistry.TargetPoint)}
    */
    public final void sendToAllAround(IMessage pMessage, NetworkRegistry.TargetPoint pPoint)
    {
        dispatcher.sendToAllAround(pMessage, pPoint);
    }

    /**
    * Sends a message to everyone within a certain range of the coordinates in the same dimension.
    */
    public final void sendToAllAround(IMessage pMessage, int pDimension, double pX, double pY, double pZ, double pRange)
    {
        sendToAllAround(pMessage, new NetworkRegistry.TargetPoint(pDimension, pX, pY, pZ, pRange));
    }

    /**
    * Sends a message to everyone within a certain range of the player provided.
    */
    public final void sendToAllAround(IMessage pMessage, EntityPlayer pPlayer, double pRange)
    {
        sendToAllAround(pMessage, pPlayer.worldObj.provider.dimensionId, pPlayer.posX, pPlayer.posY, pPlayer.posZ, pRange);
    }

    /**
    * Send this message to everyone within the supplied dimension.
    * See {@link SimpleNetworkWrapper#sendToDimension(IMessage, int)}
    */
    public final void sendToDimension(IMessage pMessage, int pDimensionId)
    {
        dispatcher.sendToDimension(pMessage, pDimensionId);
    }

    /**
    * Send this message to everyone connected to the server.
    * See {@link SimpleNetworkWrapper#sendToAll(IMessage)}
    */
    public final void sendToAll(IMessage pMessage)
    {
        dispatcher.sendToAll(pMessage);
    }
    
    /**
    * Send this message to the server.
    * See {@link SimpleNetworkWrapper#sendToServer(IMessage)}
    */
    public final void sendToServer(IMessage pMessage)
    {
        dispatcher.sendToServer(pMessage);
    }    
}

