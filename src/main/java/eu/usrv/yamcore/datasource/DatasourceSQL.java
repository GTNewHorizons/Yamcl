
package eu.usrv.yamcore.datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import eu.usrv.yamcore.auxiliary.LogHelper;
import eu.usrv.yamcore.datasource.bridge.BridgeMySQL;
import eu.usrv.yamcore.datasource.bridge.BridgeSQL;

/**
 * Datasource class which contains most functionality needed for a database connection. Database connection
 * initialization is done on instantiation. Extend this and add all the load/save methods you want right in the extended
 * class.
 */
public abstract class DatasourceSQL {

    protected LogHelper _mLog;

    protected String prefix = "";
    protected BridgeMySQL bridge;
    protected Schema _mSchema;

    public DatasourceSQL(LogHelper pLogger, Schema pSchema, String pHost, String pDBUser, String pDBPasswd,
            String pDBName) {
        _mLog = pLogger;
        _mSchema = pSchema;
        bridge = new BridgeMySQL(pHost, pDBUser, pDBPasswd, pDBName);
        _mSchema.initializeUpdates(bridge);

        try {
            doUpdates();
        } catch (SQLException ex) {
            _mLog.error("Failed to run database updates!");
            _mLog.error(ExceptionUtils.getStackTrace(ex));
        }
        loadAll();
        checkAll();
    }

    public abstract boolean loadAll();

    public abstract boolean checkAll();

    public boolean stop() {
        try {
            bridge.getConnection().close();
            return true;
        } catch (SQLException e) {
            _mLog.error("Failed to close connection to database.");
            _mLog.error(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    protected boolean hasTable(String tableName) {
        try {
            DatabaseMetaData meta = bridge.getConnection().getMetaData();
            ResultSet rs = meta.getTables(null, null, prefix + tableName, null);
            return rs.next();
        } catch (Exception ex) {
            _mLog.error("Failed to check for table existence.");
            _mLog.error(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    protected PreparedStatement prepare(String sql, boolean returnGenerationKeys) {
        try {
            return bridge.getConnection().prepareStatement(
                    sql,
                    returnGenerationKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        } catch (SQLException e) {
            _mLog.fatal(sql);
            _mLog.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    protected void doUpdates() throws SQLException {
        List<String> ids = new ArrayList<String>();
        PreparedStatement statement;
        if (hasTable("Updates")) {
            statement = prepare("SELECT id FROM " + prefix + "Updates", false);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString("id"));
            }
        }

        for (Schema.DBUpdate update : _mSchema.updates) {
            if (ids.contains(update.id)) {
                continue; // Skip if update is already done
            }

            try {
                _mLog.info(String.format("Running update %s - %s", update.id, update.desc));
                statement = prepare(update.statement, false);
                statement.execute();

                // Insert the update key so as to not run the update again
                statement = prepare("INSERT INTO " + prefix + "Updates (id,description) VALUES(?,?)", true);
                statement.setString(1, update.id);
                statement.setString(2, update.desc);
                statement.executeUpdate();
            } catch (SQLException e) {
                _mLog.error(String.format("Update (%s - %s) failed to apply!", update.id, update.desc));
                _mLog.error(ExceptionUtils.getStackTrace(e));
                throw e; // Throws back up to force safemode
            }
        }
    }

    public BridgeSQL getBridge() {
        return this.bridge;
    }
}
