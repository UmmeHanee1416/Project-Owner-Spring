package com.projectOwner.Project_owner.DTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private String email;
}
