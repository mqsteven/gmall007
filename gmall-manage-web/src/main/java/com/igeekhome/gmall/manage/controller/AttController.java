package com.igeekhome.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeekhome.gmall.bean.PmsBaseAttrInfo;
import com.igeekhome.gmall.bean.PmsBaseAttrValue;
import com.igeekhome.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AttController {

    @Reference
    AttrService attrService;
    @GetMapping("attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos=  attrService.attrInfoList(catalog3Id);
        return  pmsBaseAttrInfos;
    }

    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){

              String resule=attrService.saveAttrInfo(pmsBaseAttrInfo);
        return resule;
    }
    @PostMapping("getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){

        List<PmsBaseAttrValue>   pmsBaseAttrValues=attrService.getAttrValueList(attrId);
        return pmsBaseAttrValues;
    }
}
