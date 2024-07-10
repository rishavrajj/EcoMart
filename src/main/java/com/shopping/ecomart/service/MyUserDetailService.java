package com.shopping.ecomart.service;

import com.shopping.ecomart.entity.MyUser;
import com.shopping.ecomart.entity.Role;
import com.shopping.ecomart.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<MyUser> userList = repository.findByUserName(username);
        if (!userList.isEmpty()) {
            var userObj=userList.get(0);
            return User.builder()
                    .username(userObj.getUserName())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser user) {
        if (user.getRoles().isEmpty()) {
            return new String[]{"USER"};
        }
        return user.getRoles().stream().map(Role::getRoleName).toArray(String []::new);
    }
}