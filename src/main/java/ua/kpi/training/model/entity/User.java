package ua.kpi.training.model.entity;

import ua.kpi.training.model.entity.enums.UserType;

import java.util.Objects;

/**
 * Class User
 * DTO for User
 *
 * @author Anton Makukhin
 */
public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String firstNameUA;
    private String lastNameUA;
    private boolean enabled;
    private boolean admin;
    private int testsCompleted;
    private int averageEvaluation;
    private UserType authority;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getAuthority() {
        return authority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(UserType authority) {
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (firstNameUA == null || firstNameUA.equals("")){
            this.firstNameUA = getFirstName();
        }
    }

    public String getLastNameUA() {
        return lastNameUA;
    }

    public void setLastNameUA(String lastNameUA) {
        this.lastNameUA = lastNameUA;
        if (lastNameUA == null || lastNameUA.equals("")){
            this.lastNameUA = getLastName();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
        this.authority = admin ? UserType.ADMIN : UserType.USER;
    }

    public int getTestsCompleted() {
        return testsCompleted;
    }

    public void setTestsCompleted(int testsCompleted) {
        this.testsCompleted = testsCompleted;
    }

    public int getAverageEvaluation() {
        return averageEvaluation;
    }

    public void setAverageEvaluation(int averageEvaluation) {
        this.averageEvaluation = averageEvaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 37 * result + Objects.hash(getId());
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstNameUA='" + firstNameUA + '\'' +
                ", lastNameUA='" + lastNameUA + '\'' +
                ", enabled=" + enabled +
                ", admin=" + admin +
                ", testsCompleted=" + testsCompleted +
                ", averageEvaluation=" + averageEvaluation +
                ", authority=" + authority +
                '}';
    }
}
