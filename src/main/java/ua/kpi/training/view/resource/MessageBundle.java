package ua.kpi.training.view.resource;

import java.util.ResourceBundle;

public class MessageBundle {

    private static ResourceBundle bundle = ResourceBundle.getBundle(MessageKey.PROPERTIES);

    public MessageBundle() {
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static void setMessageBundle(ResourceBundle bundle) {
        MessageBundle.bundle = bundle;
    }

    public static String getMessage(String key){
        return bundle.getString(key);
    }

}
