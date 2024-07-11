package com.shopping.ecomart.service;

import com.shopping.ecomart.dtos.ResultResponseDTO;
import com.shopping.ecomart.dtos.UserRegsReqDTO;
import com.shopping.ecomart.dtos.UserRegsRespDTO;
import com.shopping.ecomart.entity.MyUser;
import com.shopping.ecomart.entity.Role;
import com.shopping.ecomart.repository.UserRepository;
import com.shopping.ecomart.util.ApplicationConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResultResponseDTO save(UserRegsReqDTO userDTO) {
        HashSet<Role> roles = new HashSet<>();
        userDTO.getRoles().forEach(role -> {
            roles.add(new Role(role));
        });
        MyUser myUser = new MyUser();
        myUser.setRoles(roles);
        BeanUtils.copyProperties(userDTO, myUser);
        MyUser savedUser = userRepository.save(myUser);
        UserRegsRespDTO userRegsRespDTO = new UserRegsRespDTO();
        HashSet<String> rolesRes = new HashSet<>();
        savedUser.getRoles().forEach(role->{
            rolesRes.add(role.getRoleName());
        });
        userRegsRespDTO.setRoles(rolesRes);
        BeanUtils.copyProperties(savedUser, userRegsRespDTO);
        return ResultResponseDTO.builder().message(ApplicationConstant.StatusCode.RESOURCE_CREATED)
                .response(userRegsRespDTO).build();
    }
}
