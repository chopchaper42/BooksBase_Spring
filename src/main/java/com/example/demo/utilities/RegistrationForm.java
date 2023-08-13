package com.example.demo.utilities;

import com.example.demo.data.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

@Data
public class RegistrationForm implements Serializable {

    @NotBlank(message = "Email can't be blank!")
    @Email(message = "Not a valid email!")
    private String email;

    @Length(min = 8, message = "Password should be at least 8 symbols long")
    @Pattern(regexp = "^([a-z]|[A-Z]|[0-9]|\\D){8,}$", message = "Password should contain only latin letters, digits and symbols.")
    private String password;

    @Length(min = 8, message = "Password should be at least 8 symbols long")
    @Pattern(regexp = "^([a-z]|[A-Z]|[0-9]|\\D){8,}$", message = "Password should contain only latin letters, digits and symbols.")
    private String passwordConfirmation;

    @Length(min = 5, message = "Login should be at least 5 symbols long")
    private String username;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username,
                passwordEncoder.encode(password),
                email
        );
    }
}
