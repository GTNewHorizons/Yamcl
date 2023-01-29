
package eu.usrv.yamcore.auxiliary;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import org.apache.commons.io.IOUtils;

import eu.usrv.yamcore.YAMCore;

public final class DonorController {

    private final URL _mDonorSourceURL;
    private final ArrayList<Donor> _mDonorList;

    public DonorController(URL pDonorSourceURL) {
        _mDonorList = new ArrayList<Donor>();
        _mDonorSourceURL = pDonorSourceURL;
        loadDonors();
    }

    private void loadDonors() {
        try {
            InputStream tDonorFileStream = _mDonorSourceURL.openStream();
            String tDonorDefinition = IOUtils.toString(tDonorFileStream);
            IOUtils.closeQuietly(tDonorFileStream);

            String lines[] = tDonorDefinition.split("\\r?\\n");
            for (String line : lines) {
                Donor tDonor = Donor.tryLoad(line);
                if (tDonor != null) _mDonorList.add(tDonor);
            }

        } catch (Exception e) {
            YAMCore.instance.getLogger().warn(
                    String.format("Unable to connect to %s. DonorController will not do anything!", _mDonorSourceURL));
        }
    }

    public boolean isDonor(EntityPlayer pPlayer) {
        return isDonor(pPlayer.getUniqueID());
    }

    public boolean isDonor(EntityPlayerMP pPlayer) {
        return isDonor(pPlayer.getUniqueID());
    }

    public int getLevel(EntityPlayer pPlayer) {
        return getLevel(pPlayer.getUniqueID());
    }

    public int getLevel(EntityPlayerMP pPlayer) {
        return getLevel(pPlayer.getUniqueID());
    }

    public boolean hasExtraArg(EntityPlayer pPlayer, String pDonorArg) {
        return hasExtraArg(pPlayer.getUniqueID(), pDonorArg);
    }

    public boolean hasExtraArg(EntityPlayerMP pPlayer, String pDonorArg) {
        return hasExtraArg(pPlayer.getUniqueID(), pDonorArg);
    }

    private Donor getDonor(UUID pPlayerUUID) {
        for (Donor d : _mDonorList) if (d.getUUID().equals(pPlayerUUID)) return d;

        return null;
    }

    public boolean isDonor(UUID pPlayerUUID) {
        return (getDonor(pPlayerUUID) != null);
    }

    public boolean hasExtraArg(UUID pPlayerUUID, String pDonorArg) {
        Donor d = getDonor(pPlayerUUID);
        return (d != null && d._mDonorExtraArgs.contains(pDonorArg));
    }

    public int getLevel(UUID pPlayerUUID) {
        Donor d = getDonor(pPlayerUUID);
        return (d != null ? d.getLevel() : -1);
    }

    private static final class Donor {

        private UUID _mUUID;
        private int _mLevel;
        private List<String> _mDonorExtraArgs;

        public UUID getUUID() {
            return _mUUID;
        }

        public int getLevel() {
            return _mLevel;
        }

        public List<String> getDonorArgs() {
            return Collections.unmodifiableList(_mDonorExtraArgs);
        }

        public static Donor tryLoad(String pDonorLine) {
            String lineArgs[] = pDonorLine.split("#");
            UUID tUUID = null;
            int tLevel = 0;
            ArrayList tArgs;
            /*
             * YAMCore.instance.getLogger().info( String.format( "RawLine: %s", pDonorLine ) ); if( lineArgs.length > 0
             * ) YAMCore.instance.getLogger().info( String.format( "LineArg[0]: %s", lineArgs[0] ) ); if(
             * lineArgs.length > 1 ) YAMCore.instance.getLogger().info( String.format( "LineArg[1]: %s", lineArgs[1] )
             * ); if( lineArgs.length > 2 ) YAMCore.instance.getLogger().info( String.format( "LineArg[2]: %s",
             * lineArgs[2] ) );
             */
            try {
                if (lineArgs.length > 0) tUUID = UUID.fromString(lineArgs[0]);
            } catch (Exception e) {
                YAMCore.instance.getLogger()
                        .error(String.format("Invalid PlayerUUID found in DonorFile: %s", lineArgs[0]));
            }

            try {
                if (lineArgs.length > 1) {
                    if (!IntHelper.tryParse(lineArgs[1])) YAMCore.instance.getLogger().error(
                            String.format(
                                    "Second argument in DonorLine is not an integer: %s DonorLevel will default to 0",
                                    lineArgs[1]));
                    else tLevel = Integer.parseInt(lineArgs[1]);
                }
            } catch (Exception e) {
                tLevel = 0;
            }

            try {
                if (lineArgs.length > 2) tArgs = new ArrayList<String>(Arrays.asList(lineArgs[2].split("!")));
                else tArgs = new ArrayList<String>();
            } catch (Exception e) {
                tArgs = new ArrayList<String>();
            }

            if (tUUID != null) return new Donor(tUUID, tLevel, tArgs);
            else return null;
        }

        private Donor(UUID pUserUUID, int pDonationLevel, ArrayList<String> pExtraArgs) {
            _mUUID = pUserUUID;
            _mLevel = pDonationLevel;
            _mDonorExtraArgs = pExtraArgs;
        }
    }
}
