package com.mary.ajo.payload.response;

import com.mary.ajo.models.Role;
import com.mary.ajo.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserResponse {

    private String message;

    private String lastName;

    private String firstName;

    private  String emailAddress;

    private List<Role> roles;

    public static UserResponse build(User user){
        return new UserResponse (
                "Registration Successful",
                user.getLastName(),
                user.getFirstName(),
                user.getEmailAddress(),
                user.getRoles()
        );
    }
}