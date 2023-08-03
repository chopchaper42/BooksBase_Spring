package com.example.demo;

import com.example.demo.data.User;
import jakarta.validation.constraints.Email;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    @NotBlank(message = "Email can't be blank!")
    @Email(message = "Not a valid email!")
//    @Pattern(regexp = "^[a-z][A-Z][0-9]@[a-z][A-Z][0-9].[a-z][A-Z][0-9]$")
    private String email;
    @Length(min = 8, message = "Password should be at least 8 symbols long")
    private String password;
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
