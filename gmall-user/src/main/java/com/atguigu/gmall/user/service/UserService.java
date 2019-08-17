package com.atguigu.gmall.user.service;

import com.atguigu.gmall.user.common.bean.UmsMember;


import java.util.List;

public interface UserService  {
    List<UmsMember> getAllUser();
    int deleteUserByName(int id);
    int addUser(UmsMember umsMember);
     int updateUser(UmsMember umsMember);
}
