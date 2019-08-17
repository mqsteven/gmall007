package com.igeekhome.gmall.service;



import com.igeekhome.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserAddressService {
    List<UmsMemberReceiveAddress> getAllAddress();
    int deleteUserByName(int id);
    int addUser(UmsMemberReceiveAddress umsMemberReceiveAddress);
    int updateUser(UmsMemberReceiveAddress umsMemberReceiveAddress);

    UmsMemberReceiveAddress getAddressById(String memberId);
}
