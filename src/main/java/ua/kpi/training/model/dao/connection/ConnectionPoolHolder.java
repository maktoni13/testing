package ua.kpi.training.model.dao.connection;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import ua.kpi.training.controller.command.utility.ConfigurationContainer;

/**
 * Class Connection Pool Holder
 * <p> Contains datasource initiation with configured information
 * about connection pool
 *
 * @author Anton Makukhin
 */
public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(ConfigurationContainer.JDBC_CONNECT_URL);
                    ds.setUsername(ConfigurationContainer.DBMS_USERNAME);
                    ds.setPassword(ConfigurationContainer.DBMS_PASSWORD);
                    ds.setMinIdle(ConfigurationContainer.MIN_IDLE_CONNECTIONS);
                    ds.setMaxIdle(ConfigurationContainer.MAX_IDLE_CONNECTIONS);
                    ds.setMaxOpenPreparedStatements(
                            ConfigurationContainer.MAX_OPENED_PREPARE_STATEMENTS);
                    ds.setDriverClassName(
                            ConfigurationContainer.JDBC_DRIVER_CLASS_NAME);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
