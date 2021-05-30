package de.erethon.commons.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.config.CommonConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DREDatabaseProvider {

    HikariDataSource dataSource;

    public void setupDataSource(Properties properties) {
        HikariConfig config = new HikariConfig(properties);
        CommonConfig commonConfig = CommonConfig.getInstance();
        config.setMaximumPoolSize(commonConfig.getMaxConnections());
        dataSource = new HikariDataSource(config);
        MessageUtil.log("Successfully connected to data source " + dataSource);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public boolean isConnected() {
        try {
            return dataSource.getConnection() != null;
        } catch (SQLException ignored) {
            return false;
        }
    }
}
