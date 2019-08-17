package com.atguigu.gmall.user.controller;

import com.atguigu.gmall.user.common.bean.UmsMemberReceiveAddress;
import com.atguigu.gmall.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAddressController {
    @Autowired
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
