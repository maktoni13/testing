package ua.kpi.training.controller.command.dto;

import ua.kpi.training.model.entity.enums.UserType;

public class UserDTO {
    private boolean exists;
    private String username;
    private boolean enabled;
    private boolean validPassword;
    private UserType authority;

    public UserDTO() {
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserType getAuthority() {
        return authority;
    }

    public void setAuthority(UserType authority) {
        this.authority = authority;
    }

    public boolean isValidPassword() {
        return validPassword;
    }

    public void setValidPassword(boolean validPassword) {
        this.validPassword = validPassword;
    }
}
