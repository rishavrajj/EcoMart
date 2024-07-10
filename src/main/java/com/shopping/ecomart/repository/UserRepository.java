package com.shopping.ecomart.repository;

import com.shopping.ecomart.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser> findByEmail(String name);

    List<MyUser> findByUserName(String userName);
}
