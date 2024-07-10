package com.shopping.ecomart.repository;

import com.shopping.ecomart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findByRoleName(String admin);

}
