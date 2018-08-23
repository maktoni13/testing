package ua.kpi.training.model.dao.factory;

import ua.kpi.training.model.dao.IUserDAO;
import ua.kpi.training.model.dao.connection.ConnectionPoolHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDAOFactory extends AbstractDAOFactory{

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public IUserDAO createUserDAO() {
        return new JDBCUserDAO();
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
