package com.shopping.ecomart.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegsReqDTO {
    private String userName;
    private String password;
    private String email;
    private Set<String> roles= new HashSet<>();

}
