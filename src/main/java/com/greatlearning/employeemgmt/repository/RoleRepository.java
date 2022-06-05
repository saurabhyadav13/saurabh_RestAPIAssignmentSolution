package com.greatlearning.employeemgmt.repository;

import com.greatlearning.employeemgmt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
