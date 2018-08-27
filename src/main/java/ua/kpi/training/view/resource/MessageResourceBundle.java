package ua.kpi.training.view.resource;

import java.util.ResourceBundle;

public class MessageResourceBundle {
    private static ResourceBundle messageBundle = ResourceBundle.getBundle(MessageKeyContainer.MESSAGE_PROPERTIES);

    public MessageResourceBundle() {
    }

    public static ResourceBundle getMessageBundle() {
        return messageBundle;
    }

    public static void setMessageBundle(ResourceBundle messageBundle) {
        MessageResourceBundle.messageBundle = messageBundle;
    }

    public static String getMessage(String key){
        return messageBundle.getString(key);
    }
}
