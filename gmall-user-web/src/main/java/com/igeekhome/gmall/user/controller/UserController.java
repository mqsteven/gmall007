package com.igeekhome.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeekhome.gmall.bean.UmsMember;
import com.igeekhome.gmall.service.UserService;
import com.igeekhome.gmall.common.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Reference
    UserService userService;

    @RequestMapping("getAllUser")
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMembers= userService.getAllUser();
        return umsMembers;
    }
    @GetMapping("addUser")
    public String addUser(){
        UmsMember user=UmsMember.builder()
                .id("11").city("上海").gender(0).build();
        int i=0;
        try {
            i = userService.addUser(user);
        }catch (Exception e){
            return ResponseEnum.ERROR_ERROR.toString();
        }

         if(i==1){

             return ResponseEnum.SUCCESS.toString();
         }else{
             return ResponseEnum.ERROR.toString();
         }
    }



    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }
}