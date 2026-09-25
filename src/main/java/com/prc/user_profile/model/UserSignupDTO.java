package com.prc.user_profile.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDTO {
    @NotNull
    private String email;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String department;
    @NotNull
    private String gender;
    @NotNull
    private String coursename;

    @NotNull
    private String password;
    @NotNull
    private String confirmedPassword;
}
