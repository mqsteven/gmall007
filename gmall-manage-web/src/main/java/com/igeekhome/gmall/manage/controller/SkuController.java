package com.igeekhome.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeekhome.gmall.bean.PmsSkuInfo;
import com.igeekhome.gmall.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class SkuController {

    @Reference
    SkuService skuService;


    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        //将spuId封装
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());

        String success=skuService.saveSkuInfo(pmsSkuInfo);
       return "success";
    };
}
