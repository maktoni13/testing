package ua.kpi.training.model.dao.exception;

import java.sql.SQLException;

/**
 * <p>An exception that provides information about DAO level errors
 * Cover for <code>SQLException</code>, this exception should be catch on service level
 * Anton Makukhin, 2018-09-07
 */
public class DAOException extends SQLException {

    /**
     * Constructs a <code>DAOException</code> object with a given
     * <code>reason</code> and <code>cause</code>.
     * <p>
     * @param reason exception message
     * @param cause the underlying reason for this <code>DAOException</code>
     */
    public DAOException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
