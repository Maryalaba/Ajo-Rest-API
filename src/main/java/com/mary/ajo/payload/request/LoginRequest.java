package com.mary.ajo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @NotBlank(message = "email cannot be empty")
    @Email(message = "must be email")
    private String emailAddress;

    @NotBlank(message = "password cannot be empty")
    private String password;
}
