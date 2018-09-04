package ua.kpi.training.controller.command.dto;

public class RegistrationUserDTO {

    private static final String NEXT_LINE = "\n";

    private String username;
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String firstNameUA;
    private String lastNameUA;
    private StringBuilder validationResult;

    public RegistrationUserDTO() {
        this.validationResult = new StringBuilder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstNameUA() {
        return firstNameUA;
    }

    public void setFirstNameUA(String firstNameUA) {
        this.firstNameUA = firstNameUA;
    }

    public String getLastNameUA() {
        return lastNameUA;
    }

    public void setLastNameUA(String lastNameUA) {
        this.lastNameUA = lastNameUA;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public StringBuilder getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(StringBuilder validationResult) {
        this.validationResult = validationResult;
    }

    public String getValidationResultString() {
        return validationResult.toString();
    }

    public void appendValidationResult(String validationMessage){
        if (!"".equals(getValidationResultString())) {
            validationResult.append(NEXT_LINE);
        }
        validationResult.append(validationMessage);
    }

}
