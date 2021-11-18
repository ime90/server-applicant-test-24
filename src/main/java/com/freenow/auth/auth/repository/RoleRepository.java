package com.freenow.auth.auth.repository;

import com.freenow.auth.auth.models.ERole;
import com.freenow.auth.auth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
