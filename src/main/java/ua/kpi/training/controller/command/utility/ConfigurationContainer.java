package ua.kpi.training.controller.command.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public interface ConfigurationContainer {
    String LOCALE_UA = "ua_UA";
    Locale DEFAULT_LOCALE = Locale.forLanguageTag(LOCALE_UA);
    Locale[] SUPPORTED_LOCALE_ARRAY = {Locale.ENGLISH, DEFAULT_LOCALE};
    List<Locale> SUPPORTED_LOCALE_LIST = Arrays.asList(SUPPORTED_LOCALE_ARRAY);
}
