package ua.kpi.training.controller.command.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * Interface Configuration container
 * <p> Contains configuration information for application
 * Locale configuration
 * DB Connection configuration
 *
 * @author Anton Makukhin
 */
public interface ConfigurationContainer {

    /* Locale configuration */
    String LOCALE_UA = "ua";
    Locale DEFAULT_LOCALE = Locale.forLanguageTag(LOCALE_UA);
    Locale[] SUPPORTED_LOCALE_ARRAY = {Locale.ENGLISH, DEFAULT_LOCALE};
    List<Locale> SUPPORTED_LOCALE_LIST = Arrays.asList(SUPPORTED_LOCALE_ARRAY);

    /* Connection pool configuration */
    String JDBC_CONNECT_URL = "jdbc:mysql://localhost:3306/testing_db" +
            "?rewriteBatchedStatements=true" +
            "&serverTimezone=UTC" +
            "&autoReconnect=true" +
            "&useSSL=false";
    String DBMS_USERNAME = "root";
    String DBMS_PASSWORD = "root";
    int MIN_IDLE_CONNECTIONS = 5;
    int MAX_IDLE_CONNECTIONS = 30;
    int MAX_OPENED_PREPARE_STATEMENTS = 100;
    String JDBC_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
}
