package com.mary.ajo.seeder;

import com.mary.ajo.exceptions.ResourceNotFoundException;
import com.mary.ajo.repositories.RoleRepository;
import com.mary.ajo.repositories.UserRepository;
import com.mary.ajo.models.Role;
import com.mary.ajo.models.RoleType;
import com.mary.ajo.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SeedUser {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void loadUserData() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setLastName("West");
            user1.setFirstName("Kanye");
            user1.setEmailAddress("kanyedonda@gmail.com");
            user1.setPassword(passwordEncoder.encode("donda100"));
            List<Role> roles = new ArrayList<>();
            Role role = roleRepository.findRoleByType(RoleType.ADMIN)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(role);
            user1.setRoles(roles);
            userRepository.save(user1);
            log.info("User seeded");
        }
    }
}
