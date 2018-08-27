package ua.kpi.training.model.dao.resource;

import java.util.ResourceBundle;

public class DAOResourceBundle {
    private static ResourceBundle daoBundle = ResourceBundle.getBundle(DAOKeyContainer.DAO_PROPERTIES);

    public DAOResourceBundle() {
    }

    public static ResourceBundle getDaoBundle() {
        return daoBundle;
    }

    public static void setDaoBundle(ResourceBundle daoBundle) {
        DAOResourceBundle.daoBundle = daoBundle;
    }

    public static String getStatement(String key){
        return daoBundle.getString(key);
    }
}
