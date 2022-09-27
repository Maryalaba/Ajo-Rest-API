package com.mary.ajo.init;

import com.mary.ajo.models.Role;
import com.mary.ajo.models.RoleType;
import com.mary.ajo.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role role;

        if (roleRepository.findRoleByType(RoleType.ADMIN).isEmpty()) {
            role = new Role();
            role.setType(RoleType.ADMIN);
            roleRepository.save(role);
        }

        if (roleRepository.findRoleByType(RoleType.MEMBER).isEmpty()) {
            role = new Role();
            role.setType(RoleType.MEMBER);
            roleRepository.save(role);
        }
    }
}