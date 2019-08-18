package com.igeekhome.gmall.user.service.impl;


import com.igeekhome.gmall.bean.UmsMemberReceiveAddress;
import com.igeekhome.gmall.service.UserAddressService;
import com.igeekhome.gmall.user.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UmsMemberReceiveAddress> getAllAddress() {
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = userAddressMapper.selectList(null);
        return umsMemberReceiveAddresses;
    }

    @Override
    public int deleteUserByName(int id) {
        return 0;
    }

    @Override
    public int addUser(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return 0;
    }

    @Override
    public int updateUser(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return 0;
    }

    @Override
    public UmsMemberReceiveAddress getAddressById(String memberId) {
        UmsMemberReceiveAddress UmsList = userAddressMapper.selectById(memberId);
        return UmsList;
    }
}
