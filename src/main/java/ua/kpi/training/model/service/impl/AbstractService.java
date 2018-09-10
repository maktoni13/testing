package ua.kpi.training.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.DAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>An abstract level service that provides performing service operation covered by transaction
 * Template method pattern is used
 *
 * @author Anton Makukhin
 * @param <I> input type for template method
 * @param <O> output type for template method
 */
public abstract class AbstractService<I, O> implements AutoCloseable {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(AbstractService.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    private Connection connection;

    protected AbstractService() {
    }

    /**
     * Template method for service operation performing with transaction with SERIALIZABLE isolation,
     * should be covered in service interface method with exception handling
     * @param input input value
     * @return output value
     * @throws SQLException connection and DAO errors should be covered on interface service level
     */
    O performServiceInSerializableTransaction(I input) throws SQLException{
        return performServiceInTransaction(input,
                Connection.TRANSACTION_SERIALIZABLE);
    }

    /**
     * Template method for service operation performing with transaction with REPEATABLE_READ isolation,
     * should be covered in service interface method with exception handling
     * @param input input value
     * @return output value
     * @throws SQLException connection and DAO errors should be covered on interface service level
     */
    protected O performServiceInRepeatableReadTransaction(I input) throws SQLException{
        return performServiceInTransaction(input,
                Connection.TRANSACTION_REPEATABLE_READ);
    }

    /**
     * Template method for service operation performing with transaction,
     * should be covered in service interface method with exception handling
     * @param input input value
     * @param isolationType transaction isolation type
     * @return output value
     * @throws SQLException connection and DAO errors should be covered on interface service level
     */
    private O performServiceInTransaction(I input, int isolationType) throws SQLException{
        O output = null;
        connection = daoFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(isolationType);
            output = performService(input);
            connection.commit();
        } catch (Exception e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return output;
    }

    /**
     * Sub operation for template method. should be implemented by successors
     * @param input input value
     * @return output value
     * @throws SQLException connection and DAO errors should be covered on interface service level
     */
    protected abstract O performService(I input) throws SQLException;

    @Override
    public void close(){
        if (connection != null){
            try{
                if(connection.getAutoCommit()){
                    connection.setAutoCommit(true);
                }
                connection.close();
            } catch (SQLException e){
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_CONNECTION_CLOSING, e);
                throw new RuntimeException(e); // TODO: Move closing connection to daoFactory.
            }
        }
    }

}
