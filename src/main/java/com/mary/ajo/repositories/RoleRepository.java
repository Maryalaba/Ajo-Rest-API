package com.mary.ajo.repositories;

import com.mary.ajo.models.Role;
import com.mary.ajo.models.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByType(RoleType type);
}

