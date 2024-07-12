package com.shopping.ecomart.service;

import com.shopping.ecomart.dtos.ResultResponseDTO;
import com.shopping.ecomart.dtos.UserRegsReqDTO;
import com.shopping.ecomart.dtos.UserRegsRespDTO;
import com.shopping.ecomart.entity.MyUser;
import com.shopping.ecomart.entity.Role;
import com.shopping.ecomart.exception.RoleNotFoundException;
import com.shopping.ecomart.repository.RoleRepository;
import com.shopping.ecomart.repository.UserRepository;
import com.shopping.ecomart.util.ApplicationConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ResultResponseDTO save(UserRegsReqDTO userDTO) {
        Set<Role> existingRoles=null;
        existingRoles = getDbRoles(userDTO, existingRoles);
        MyUser myUser = MyUser.builder().roles(existingRoles).build();
        BeanUtils.copyProperties(userDTO, myUser);
        MyUser savedUser = userRepository.save(myUser);
        Set<String> rolesRes = setRolesAsString(savedUser);
        UserRegsRespDTO userRegsRespDTO = UserRegsRespDTO.builder().roles(rolesRes).build();
        BeanUtils.copyProperties(savedUser, userRegsRespDTO);
        return ResultResponseDTO.builder().message(ApplicationConstant.StatusCode.RESOURCE_CREATED)
                .response(userRegsRespDTO).build();
    }

    private static Set<String> setRolesAsString(MyUser savedUser) {
        Set<String> rolesRes = new HashSet<>();
        savedUser.getRoles().forEach(role -> {
            rolesRes.add(role.getRoleName());
        });
        return rolesRes;
    }

    private Set<Role> getDbRoles(UserRegsReqDTO userDTO, Set<Role> byRoleName) {
        for (String role : userDTO.getRoles()) {
            byRoleName = new HashSet<>(roleRepository.findByRoleName(role));
            if (byRoleName.isEmpty()) {
                throw new RoleNotFoundException(role);
            }
        }
        return byRoleName;
    }
}
