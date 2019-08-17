package com.atguigu.gmall.user.service.impl;

import com.atguigu.gmall.user.common.bean.UmsMember;
import com.atguigu.gmall.user.mapper.UserMapper;
import com.atguigu.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMembers=userMapper.selectList(null); //userMapper.selectALLUser();
        return umsMembers;
    }

    @Override
    public int deleteUserByName(int id) {
        int i = userMapper.deleteById(id);
        return i;
    }

    @Override
    public int addUser(UmsMember umsMember) {
        int i = userMapper.insert(umsMember);
        return i;
    }

    @Override
    public int updateUser(UmsMember umsMember) {
        int i = userMapper.updateById(umsMember);
        return i;
    }



}
