package com.mary.ajo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class BaseUser extends BaseEntity {

    @NotBlank(message = "last name can not be blank")
    @Column(name = "last_name", nullable = false)
    public String lastName;

    @NotBlank(message = "first name can not be blank")
    @Column(name = "first_name", nullable = false)
    public String firstName;

    @JsonIgnore
    @Size(min = 6, message = "password length must be more than 5")
    @Column(name = "password", nullable = false)
    public String password;

    @Email(message = "Email must have valid format")
    @Column(name = "email_address", nullable = false, unique = true)
    public String emailAddress;
}
