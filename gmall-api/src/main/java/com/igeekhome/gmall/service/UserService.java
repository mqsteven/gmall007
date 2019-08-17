package com.igeekhome.gmall.service;




import com.igeekhome.gmall.bean.UmsMember;

import java.util.List;

public interface UserService  {
    List<UmsMember> getAllUser();
    int deleteUserByName(int id);
    int addUser(UmsMember umsMember);
     int updateUser(UmsMember umsMember);
}
