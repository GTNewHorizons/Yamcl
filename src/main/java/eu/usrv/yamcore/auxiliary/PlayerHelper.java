
package eu.usrv.yamcore.auxiliary;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.FakePlayer;

import eu.usrv.yamcore.YAMCore;

/**
 * Various functions for/with Players (EntityPlayer)
 *
 * @author Namikon
 *
 */
public class PlayerHelper {

    /**
     * Remove protection from the player
     *
     * @param pEntityPlayer
     */
    public static void RemoveProtection(EntityPlayer pEntityPlayer) {
        RemoveProtection(pEntityPlayer, true);
    }

    /**
     * Actually GIVE protection to the player and notify the player
     *
     * @param pEntityPlayer
     */
    public static void GiveProtection(EntityPlayer pEntityPlayer) {
        GiveProtection(pEntityPlayer, true);
    }

    /**
     * Remove protection from the player
     *
     * @param pEntityPlayer
     */
    public static void RemoveProtection(EntityPlayer pEntityPlayer, boolean pNotifyPlayer) {
        try {
            pEntityPlayer.capabilities.disableDamage = false;
            pEntityPlayer.sendPlayerAbilities();
            if (pNotifyPlayer)
                PlayerChatHelper.SendNotifyWarning(pEntityPlayer, StatCollector.translateToLocal("yamcore.char.protection.remove"));
        } catch (Exception e) {
            YAMCore.instance.getLogger().error("RemoveProtection failed to Remove player's protection");
            YAMCore.instance.getLogger().DumpStack(e);
        }
    }

    /**
     * Actually GIVE protection to the player
     *
     * @param pEntityPlayer
     * @param pNotifyPlayer notify the player about the change
     */
    public static void GiveProtection(EntityPlayer pEntityPlayer, boolean pNotifyPlayer) {
        try {
            pEntityPlayer.capabilities.disableDamage = true;
            pEntityPlayer.sendPlayerAbilities();
            if (pNotifyPlayer)
                PlayerChatHelper.SendNotifyPositive(pEntityPlayer, StatCollector.translateToLocal("yamcore.char.protection.give"));
        } catch (Exception e) {
            YAMCore.instance.getLogger().error("RemoveProtection failed to give a player protection");
            YAMCore.instance.getLogger().DumpStack(e);
        }
    }

    /**
     * Add a distance of (approximately) pDistance blocks to a players current location. Height (y) is not affected by
     * this, only X/Z Coordinates are. The current direction (North/South/East/West, also known as "Yaw") is used to
     * calculate the new location
     *
     * Note: This is usually used Server-Side, which means the Result might be "wrong". This is because the server isn't
     * always up to date about a players exact Yaw. If you want a more accurate result, use addDistanceByVecAndYaw() and
     * grab the current Yaw by other means (Custom Packets,..)
     *
     * @param pPlayer   The player; Source location
     * @param pDistance The distance (in blocks) to be added
     * @return A Vector describing the new location
     */
    public static Vec3 addDistanceByPlayerDirection(EntityPlayer pPlayer, int pDistance) {
        if (pPlayer instanceof FakePlayer) throw new IllegalArgumentException("pPlayer cannot be a FakePlayer!");

        Vec3 tVec = Vec3.createVectorHelper(pPlayer.posX, pPlayer.posY, pPlayer.posZ);

        return addDistanceByVecAndYaw(tVec, pPlayer.rotationYaw, pDistance);
    }

    /**
     * Add a distance of (approximately) pDistance blocks to a given Vector. Height (y) is not affected by this, only
     * X/Z Coordinates are. The current direction (North/South/East/West, also known as "Yaw") is used to calculate the
     * new location
     *
     * @param pSourceLocation The source location as Vector
     * @param pYaw            The Yaw (Usually taken from rotationYaw at EntityPlayer)
     * @param pDistance       The distance (in blocks) to be added
     * @return A Vector describing the new location
     */
    public static Vec3 addDistanceByVecAndYaw(Vec3 pSourceLocation, double pYaw, int pDistance) {
        double x = 0;
        double z = 0;

        double rotation = (pYaw - 90) % 360;
        if (rotation < 0) rotation += 360.0;

        x = Math.floor(Math.cos(rotation * Math.PI / 180) * pDistance) * -1;
        z = Math.floor(Math.sin(rotation * Math.PI / 180) * pDistance) * -1;

        return pSourceLocation.addVector(x, 0, z);
    }
}
