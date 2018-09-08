package ua.kpi.training.model.dao.connection;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
// TODO: Add properties or Move connection pool to META-INF/context.xml
public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/testing_db?serverTimezone=UTC&autoReconnect=true&useSSL=false");
                    ds.setUsername("root");
                    ds.setPassword("root");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(30);
                    ds.setMaxOpenPreparedStatements(100);
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }


}
