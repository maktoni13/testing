package ua.kpi.training.view.resource;

public interface MessageKey {
    String PROPERTIES = "messages";
    String USERNAME_REGEX_ERR = "messages.username.regex.error";
    String PASSWORD_REGEX_ERR = "messages.password.regex.error";
    String EMAIL_REGEX_ERR = "messages.email.regex.error";
    String FIRST_NAME_REGEX_ERR = "messages.first.name.regex.error";
    String LAST_NAME_REGEX_ERR = "messages.last.name.regex.error";
    String FIRST_NAME_UA_REGEX_ERR = "messages.first.name.ua.regex.error";
    String LAST_NAME_UA_REGEX_ERR = "messages.last.name.ua.regex.error";
    String PASSWORDS_NOT_EQUAL_ERR = "messages.passwords.not.equal";
    String EMAILS_NOT_EQUAL_ERR = "messages.emails.not.equal";

    String USERNAME_ALREADY_REGISTERED = "messages.username.already.registered";
    String EMAIL_ALREADY_REGISTERED = "messages.email.already.registered";

    String INCORRECT_USERNAME_OR_PASSWORD = "messages.incorrect.username.or.password";
    String USER_IS_DISABLED = "messages.user.is.disabled";
    String LOGIN_EMPTY_ERROR = "messages.login.empty.error";
    String LOGIN_USER_ALREADY_LOGGED_ERROR = "messages.login.user.already.logged";
    String LOGIN_SHOULD_LOGOUT_FIRST = "messages.login.should.logout.first";
    String SQL_ERROR = "messages.sql.error";

    String SQL_ERROR_PLEASE_TRY_AGAIN = "";
}
