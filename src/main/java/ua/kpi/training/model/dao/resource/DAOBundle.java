package ua.kpi.training.model.dao.resource;

import java.util.ResourceBundle;

/**
 * Abstract class for DAO Factory
 * Using for creating of DAO objects
 * @author Anton Makukhin
 */
public class DAOBundle {
    private static ResourceBundle daoBundle = ResourceBundle.getBundle(DAOKey.DAO_PROPERTIES);

    public DAOBundle() {
    }

    public static ResourceBundle getDaoBundle() {
        return daoBundle;
    }

    public static void setDaoBundle(ResourceBundle daoBundle) {
        DAOBundle.daoBundle = daoBundle;
    }

    public static String getStatement(String key){
        return daoBundle.getString(key);
    }
}
