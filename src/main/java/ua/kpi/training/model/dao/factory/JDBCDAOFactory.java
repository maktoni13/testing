package ua.kpi.training.model.dao.factory;

import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.TestDAO;
import ua.kpi.training.model.dao.ThemeDAO;
import ua.kpi.training.model.dao.UserDAO;
import ua.kpi.training.model.dao.connection.ConnectionPoolHolder;
import ua.kpi.training.model.dao.impl.JDBCTestDAO;
import ua.kpi.training.model.dao.impl.JDBCThemeDAO;
import ua.kpi.training.model.dao.impl.JDBCUserDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDAOFactory extends DAOFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDAO createUserDAO() {
        return new JDBCUserDAO(getConnection());
    }

    @Override
    public ThemeDAO createThemeDAO() {
        return new JDBCThemeDAO(getConnection());
    }

    @Override
    public TestDAO createTestDAO() {
        return new JDBCTestDAO(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // TODO: Exception
        }
    }


}
