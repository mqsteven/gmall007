package com.igeekhome.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.igeekhome.gmall.bean.PmsBaseAttrInfo;
import com.igeekhome.gmall.bean.PmsProductInfo;
import com.igeekhome.gmall.manage.mapper.PmsProductInfoMapper;
import com.igeekhome.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {


        QueryWrapper<PmsProductInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductInfo::getCatalog3Id,catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.selectList(queryWrapper);

        return pmsProductInfos;
    }
}
