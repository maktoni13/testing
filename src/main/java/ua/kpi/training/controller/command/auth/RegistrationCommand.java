package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.dto.RegistrationUserDTO;
import ua.kpi.training.controller.command.regex.RegexContainer;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.exception.NonUniqueUserException;
import ua.kpi.training.model.service.CommonService;
import ua.kpi.training.model.service.RegistrationService;
import ua.kpi.training.model.service.factory.ServiceFactory;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegistrationCommand implements Command {

    private RegistrationUserDTO extractRegistrationUserDTO(HttpServletRequest request){
        RegistrationUserDTO regUserDTO = new RegistrationUserDTO();
        regUserDTO.setUsername(
                request.getParameter(PageContainer.REG_PARAM_USERNAME));
        regUserDTO.setPassword(
                request.getParameter(PageContainer.REG_PARAM_PASSWORD));
        regUserDTO.setConfirmPassword(
                request.getParameter(PageContainer.REG_PARAM_CONFIRM_PASSWORD));
        regUserDTO.setEmail(
                request.getParameter(PageContainer.REG_PARAM_EMAIL));
        regUserDTO.setConfirmEmail(
                request.getParameter(PageContainer.REG_PARAM_CONFIRM_EMAIL));
        regUserDTO.setFirstName(
                request.getParameter(PageContainer.REG_PARAM_FIRST_NAME));
        regUserDTO.setFirstNameUA(
                request.getParameter(PageContainer.REG_PARAM_FIRST_NAME_UA));
        regUserDTO.setLastName(
                request.getParameter(PageContainer.REG_PARAM_LAST_NAME));
        regUserDTO.setLastNameUA(
                request.getParameter(PageContainer.REG_PARAM_LAST_NAME_UA));
        return regUserDTO;
    }

    private boolean matchWithRegex(String inputValue, String regexExpression){
        return Pattern.compile(regexExpression).matcher(inputValue).matches();
    }

    private String getValidationResult(String value, String regex, String message){
        return matchWithRegex(value, regex) ? "" : message;
    }

    private boolean isUserDataCorrect(RegistrationUserDTO regUserDTO){
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
        if (!regUserDTO.getPassword().equals(regUserDTO.getConfirmPassword())){
            regUserDTO.appendValidationResult(
                    MessageBundle.getMessage(MessageKey.PASSWORDS_NOT_EQUAL_ERR));
        }
        if (!regUserDTO.getEmail().equals(regUserDTO.getConfirmEmail())){
            regUserDTO.appendValidationResult(
                    MessageBundle.getMessage(MessageKey.EMAILS_NOT_EQUAL_ERR));
        }
        return "".equals(regUserDTO.getValidationResultString());
    }

    private boolean isRegistrationSuccessful(RegistrationUserDTO regUserDTO){
        RegistrationService regService = (RegistrationService) getService();
        try {
            return regService.registerUser(regUserDTO);
        } catch (NonUniqueUserException e){
            regUserDTO.appendValidationResult(e.getMessage());
            return false;
        }
    }

    private void saveRegistrationData(HttpServletRequest request, RegistrationUserDTO regUserDTO){
        request.setAttribute(PageContainer.REG_ATTR_USERNAME,
                regUserDTO.getUsername());
        request.setAttribute(PageContainer.REG_ATTR_PASSWORD,
                regUserDTO.getPassword());
        request.setAttribute(PageContainer.REG_ATTR_CONFIRM_PASSWORD,
                regUserDTO.getConfirmPassword());
        request.setAttribute(PageContainer.REG_ATTR_EMAIL,
                regUserDTO.getEmail());
        request.setAttribute(PageContainer.REG_ATTR_CONFIRM_EMAIL,
                regUserDTO.getConfirmEmail());
        request.setAttribute(PageContainer.REG_ATTR_FIRST_NAME,
                regUserDTO.getFirstName());
        request.setAttribute(PageContainer.REG_ATTR_FIRST_NAME_UA,
                regUserDTO.getFirstNameUA());
        request.setAttribute(PageContainer.REG_ATTR_LAST_NAME,
                regUserDTO.getLastName());
        request.setAttribute(PageContainer.REG_ATTR_LAST_NAME_UA,
                regUserDTO.getLastNameUA());
        request.setAttribute(PageContainer.REG_ATTR_ERROR_MESSAGE,
                regUserDTO.getValidationResult());
    }

    @Override
    public CommonService getService() {
          return ServiceFactory.getInstance()
                    .getRegistrationService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = PageContainer.PAGE_REGISTRATION;
        RegistrationUserDTO regUserDTO = extractRegistrationUserDTO(request);
        if (isUserDataCorrect(regUserDTO) && isRegistrationSuccessful(regUserDTO)){
            page = PageContainer.LOGIN_PAGE_PATH;
        } else {
            saveRegistrationData(request, regUserDTO);
        }
        return page;
    }


}
