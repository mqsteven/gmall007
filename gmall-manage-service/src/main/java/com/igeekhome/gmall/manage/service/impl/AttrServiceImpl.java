package com.igeekhome.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.igeekhome.gmall.bean.PmsBaseAttrInfo;
import com.igeekhome.gmall.bean.PmsBaseAttrValue;
import com.igeekhome.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.igeekhome.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.igeekhome.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;




    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        QueryWrapper<PmsBaseAttrInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsBaseAttrInfo::getCatalog3Id,catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.selectList(queryWrapper);

        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        String id = pmsBaseAttrInfo.getId();
        if(StringUtils.isBlank(id)){

            int insert = pmsBaseAttrInfoMapper.insert(pmsBaseAttrInfo);
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insert(pmsBaseAttrValue);
            }
        }else{
            int i = pmsBaseAttrInfoMapper.updateById(pmsBaseAttrInfo);

            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            //按照属性id删除所有属性值
            QueryWrapper<PmsBaseAttrValue> queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(PmsBaseAttrValue::getAttrId,pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(queryWrapper);
            //循环插入
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValueMapper.insert(pmsBaseAttrValue);
            }
        }


        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        QueryWrapper<PmsBaseAttrValue> query=new QueryWrapper<>();
        query.lambda().eq(PmsBaseAttrValue::getAttrId,attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.selectList(query);

        return pmsBaseAttrValues;
    }


}