package com.bjpowernode.mapper;

import com.bjpowernode.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectUserPage(
            @Param("userName")
            String userName,
            @Param("userSex")
            String userSex,
            @Param("startRow") // 算好的起始行的值
            int startRow);

    int createUser(User user);

    int deleteUserById(String userId);

    int getRowCount(
            @Param("userName")
            String userName,
            @Param("userSex")
            String userSex);
}
