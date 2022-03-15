package ir.ac.alzahra.onlineexam.dto;

import ir.ac.alzahra.onlineexam.validators.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserDto {

    @NotEmpty(message = "{not.empty}")
    @Size(min = 3, max = 15, message = "{size}")
    protected String firstName;

    @NotEmpty(message = "{not.empty}")
    @Size(min = 3, max = 45, message = "{size}")
    protected String lastName;

    @NotEmpty(message = "{not.empty}")
    @Email(message = "{email.well-formed}")
    protected String email;

    @Password
    protected String password;

    protected Integer role;

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public UserDto setRole(Integer role) {
        this.role = role;
        return this;
    }
}
