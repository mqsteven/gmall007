package com.igeekhome.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeekhome.gmall.bean.UmsMemberReceiveAddress;
import com.igeekhome.gmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAddressController {
    @Reference
    UserAddressService userAddressService;
    @RequestMapping("getAllAddress")
    public List<UmsMemberReceiveAddress> getAddress(){
        List<UmsMemberReceiveAddress> allAddress = userAddressService.getAllAddress();
        return allAddress;
    }
    @RequestMapping("getAdressById")
    public UmsMemberReceiveAddress getAddressById(@RequestBody String MemberId){
        UmsMemberReceiveAddress addressList = userAddressService.getAddressById(MemberId);
        return  addressList;
    }


}
