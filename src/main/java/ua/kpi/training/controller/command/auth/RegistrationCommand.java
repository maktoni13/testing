package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.dto.RegistrationUserDTO;
import ua.kpi.training.controller.command.utility.RegexContainer;
import ua.kpi.training.controller.command.utility.SCryptPassHashing;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.dao.exception.NonUniqueUserException;
import ua.kpi.training.model.service.RegistrationService;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegistrationCommand implements Command {
    private static final String REG_PARAM_USERNAME = "username";
    private static final String REG_PARAM_PASSWORD = "password";
    private static final String REG_PARAM_CONFIRM_PASSWORD = "confirm-password";
    private static final String REG_PARAM_EMAIL = "email";
    private static final String REG_PARAM_CONFIRM_EMAIL = "confirm-email";
    private static final String REG_PARAM_FIRST_NAME = "first-name";
    private static final String REG_PARAM_FIRST_NAME_UA = "first-name-ua";
    private static final String REG_PARAM_LAST_NAME = "last-name";
    private static final String REG_PARAM_LAST_NAME_UA = "last-name-ua";
    private static final String REG_ATTR_USERNAME = "username";
    private static final String REG_ATTR_PASSWORD = "password";
    private static final String REG_ATTR_CONFIRM_PASSWORD = "confirmPassword";
    private static final String REG_ATTR_EMAIL = "email";
    private static final String REG_ATTR_CONFIRM_EMAIL = "confirmEmail";
    private static final String REG_ATTR_FIRST_NAME = "firstName";
    private static final String REG_ATTR_FIRST_NAME_UA = "firstNameUA";
    private static final String REG_ATTR_LAST_NAME = "lastName";
    private static final String REG_ATTR_LAST_NAME_UA = "lastNameUA";
    private static final String REG_ATTR_ERROR_MESSAGE = "registrationErrorMessage";

    private RegistrationService registrationService;

    public RegistrationCommand() {
    }

    public RegistrationCommand(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    private RegistrationUserDTO extractRegistrationUserDTO(HttpServletRequest request) {
        RegistrationUserDTO regUserDTO = new RegistrationUserDTO();
        regUserDTO.setUsername(
                request.getParameter(REG_PARAM_USERNAME));
        regUserDTO.setPassword(
                request.getParameter(REG_PARAM_PASSWORD));
        regUserDTO.setConfirmPassword(
                request.getParameter(REG_PARAM_CONFIRM_PASSWORD));
        regUserDTO.setEmail(
                request.getParameter(REG_PARAM_EMAIL));
        regUserDTO.setConfirmEmail(
                request.getParameter(REG_PARAM_CONFIRM_EMAIL));
        regUserDTO.setFirstName(
                request.getParameter(REG_PARAM_FIRST_NAME));
        regUserDTO.setFirstNameUA(
                request.getParameter(REG_PARAM_FIRST_NAME_UA));
        regUserDTO.setLastName(
                request.getParameter(REG_PARAM_LAST_NAME));
        regUserDTO.setLastNameUA(
                request.getParameter(REG_PARAM_LAST_NAME_UA));
        return regUserDTO;
    }

    private boolean matchWithRegex(String inputValue, String regexExpression) {
        return Pattern.compile(regexExpression).matcher(inputValue).matches();
    }

    private String getValidationResult(String value, String regex, String message) {
        return matchWithRegex(value, regex) ? "" : message;
    }

    private boolean isUserDataCorrect(RegistrationUserDTO regUserDTO) {
        regUserDTO.appendValidationResult(getValidationResult(
                regUserDTO.getUsername(),
                RegexContainer.USERNAME,
                MessageBundle.getMessage(MessageKey.USERNAME_REGEX_ERR)));
        regUserDTO.appendValidationResult(getValidationResult(
                regUserDTO.getPassword(),
                RegexContainer.PASSWORD,
                MessageBundle.getMessage(MessageKey.PASSWORD_REGEX_ERR)));
        regUserDTO.appendValidationResult(getValidationResult(
                regUserDTO.getEmail(),
                RegexContainer.EMAIL,
                MessageBundle.getMessage(MessageKey.EMAIL_REGEX_ERR)));
        regUserDTO.appendValidationResult(getValidationResult(
                regUserDTO.getFirstName(),
                RegexContainer.NAME,
                MessageBundle.getMessage(MessageKey.FIRST_NAME_REGEX_ERR)));
        regUserDTO.appendValidationResult(getValidationResult(
                regUserDTO.getFirstNameUA(),
                RegexContainer.NAME_UA,
                MessageBundle.getMessage(MessageKey.FIRST_NAME_UA_REGEX_ERR)));
        regUserDTO.appendValidationResult(getValidationResult(
                regUserDTO.getLastName(),
                RegexContainer.NAME,
                MessageBundle.getMessage(MessageKey.LAST_NAME_REGEX_ERR)));
        regUserDTO.appendValidationResult(getValidationResult(
                regUserDTO.getLastNameUA(),
                RegexContainer.NAME_UA,
                MessageBundle.getMessage(MessageKey.LAST_NAME_UA_REGEX_ERR)));
        if (!regUserDTO.getPassword().equals(regUserDTO.getConfirmPassword())) {
            regUserDTO.appendValidationResult(
                    MessageBundle.getMessage(MessageKey.PASSWORDS_NOT_EQUAL_ERR));
        }
        if (!regUserDTO.getEmail().equals(regUserDTO.getConfirmEmail())) {
            regUserDTO.appendValidationResult(
                    MessageBundle.getMessage(MessageKey.EMAILS_NOT_EQUAL_ERR));
        }
        boolean result = "".equals(regUserDTO.getValidationResultString());
        if (result) {
            String cryptedPassword =
                    SCryptPassHashing.cryptPass(regUserDTO.getPassword());
            regUserDTO.setPassword(cryptedPassword);
            regUserDTO.setConfirmPassword(cryptedPassword);
        } else {
            regUserDTO.setPassword("");
            regUserDTO.setConfirmPassword("");
        }
        return result;
    }

    private boolean isRegistrationSuccessful(RegistrationUserDTO regUserDTO) {
        try {
            return registrationService.registerUser(regUserDTO);
        } catch (NonUniqueUserException e) {
            regUserDTO.appendValidationResult(e.getMessage());
            return false;
        }
    }

    private void saveRegistrationData(HttpServletRequest request, RegistrationUserDTO regUserDTO) {
        request.setAttribute(REG_ATTR_USERNAME, regUserDTO.getUsername());
        request.setAttribute(REG_ATTR_PASSWORD, regUserDTO.getPassword());
        request.setAttribute(REG_ATTR_CONFIRM_PASSWORD, regUserDTO.getConfirmPassword());
        request.setAttribute(REG_ATTR_EMAIL, regUserDTO.getEmail());
        request.setAttribute(REG_ATTR_CONFIRM_EMAIL, regUserDTO.getConfirmEmail());
        request.setAttribute(REG_ATTR_FIRST_NAME, regUserDTO.getFirstName());
        request.setAttribute(REG_ATTR_FIRST_NAME_UA, regUserDTO.getFirstNameUA());
        request.setAttribute(REG_ATTR_LAST_NAME, regUserDTO.getLastName());
        request.setAttribute(REG_ATTR_LAST_NAME_UA, regUserDTO.getLastNameUA());
        request.setAttribute(REG_ATTR_ERROR_MESSAGE, regUserDTO.getValidationResult());
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = PageContainer.WEB_INF_REGISTRATION_JSP;
        if (PageContainer.HTTP_GET.equals(request.getMethod())) {
            return page;
        }
        RegistrationUserDTO regUserDTO = extractRegistrationUserDTO(request);
        if (regUserDTO != null) {
            if (isUserDataCorrect(regUserDTO)
                    && isRegistrationSuccessful(regUserDTO)) {
                page = PageContainer.PATH_PREFIX_REDIRECT +
                        PageContainer.PATH_COMMAND_LOGIN;
            } else {
                saveRegistrationData(request, regUserDTO);
            }
        }
        return page;
    }


}
