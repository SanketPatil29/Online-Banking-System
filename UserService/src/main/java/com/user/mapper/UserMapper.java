package com.user.mapper;

import com.user.DTO.UserInfoDto;
import com.user.Entities.User;

public class UserMapper {
    public static User mapToUser(UserInfoDto userInfoDto){
        User user = new User();

        user.setUsername(
                userInfoDto.getUsername()
        );
        user.setPassword(userInfoDto.getPassword());
        user.setRole(userInfoDto.getRole());
        return  user;
    }
}
