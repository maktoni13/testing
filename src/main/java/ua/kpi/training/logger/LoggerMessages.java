package ua.kpi.training.logger;

public interface LoggerMessages {
    String ERROR_SERVICE_TRANSACTION_INCOMPLETE = "Service transaction is incomplete ";
    String ERROR_SERVICE_CONNECTION_CLOSING = "Closing connection error";
    String ERROR_SERVICE_SQL_EXCEPTION = "SQL Exceptions throws during service performing";
    String ERROR_DAO_CONNECTION_CLOSE_SQL_EXCEPTION = "SQL Exceptions throws during connection closing in DAO";
    String ERROR_DAO_DELETE_QUERY = "SQL Exception throws during delete query in DAO";
    String ERROR_DAO_INSERT_QUERY = "SQL Exception throws during insert query in DAO";
    String ERROR_DAO_UPDATE_QUERY = "SQL Exception throws during update query in DAO";
    String ERROR_SCRYPT_PASSWORD_VALIDATION_PROCESS = "Error in scrypt password validation process";

    String INFO_DAO_FACTORY_CREATED = "DAO Factory created";

    String WARN_UNEXPECTED_CONNECTION_CLOSING = "Unexpected connection closing";

    String DEBUG_SAVE_THEME_ENTITY_INCOMPLETE = "Theme didn't save to database";
    String DEBUG_SAVE_TEST_ENTITY_INCOMPLETE = "Test didn't save to database";



}
