package com.shopping.ecomart.repository;

import com.shopping.ecomart.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    List<MyUser> findByUserName(String username);
}