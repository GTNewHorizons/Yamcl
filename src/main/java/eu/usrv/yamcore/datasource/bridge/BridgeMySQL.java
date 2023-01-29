
package eu.usrv.yamcore.datasource.bridge;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.mysql.jdbc.Driver;

import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.LogHelper;

public class BridgeMySQL extends BridgeSQL {

    private LogHelper _mLog = YAMCore.instance.getLogger();
    private String _mDBUser;
    private String _mDBPW;
    private String _mDBHost;
    private String _mDBDB;

    public BridgeMySQL(String pDBHost, String pDBUsername, String pDBPassword, String pDBDBName) {
        _mDBUser = pDBUsername;
        _mDBPW = pDBPassword;
        _mDBHost = pDBHost;
        _mDBDB = pDBDBName;

        initProperties();
        initConnection();
    }

    @Override
    protected void initProperties() {
        autoIncrement = "AUTO_INCREMENT";

        properties.put("autoReconnect", "true");
        properties.put("user", _mDBUser);
        properties.put("password", _mDBPW);
        properties.put("relaxAutoCommit", "true");
    }

    @Override
    protected void initConnection() {
        this.dsn = "jdbc:mysql://" + _mDBHost + "/" + _mDBDB;

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException ex) {
            _mLog.error("Failed to register driver for MySQL database.");
            _mLog.error(ExceptionUtils.getStackTrace(ex));
        }

        try {
            if (conn != null && !conn.isClosed()) {
                try {
                    conn.close();
                } catch (SQLException ex) {} // Ignore since we are just closing an old connection
                conn = null;
            }

            conn = DriverManager.getConnection(dsn, properties);
        } catch (SQLException ex) {
            _mLog.error("Failed to get SQL connection!");
            _mLog.error(ExceptionUtils.getStackTrace(ex));
        }
    }
}
