package ua.kpi.training.view.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageBundle {

    private static ResourceBundle bundle = ResourceBundle.getBundle(MessageKey.PROPERTIES);

    public MessageBundle() {
    }

    public static void updateByDefaultLocale(){
        bundle = ResourceBundle.getBundle(MessageKey.PROPERTIES, Locale.getDefault());
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }


    public static String getMessage(String key){
        return bundle.getString(key);
    }

}
