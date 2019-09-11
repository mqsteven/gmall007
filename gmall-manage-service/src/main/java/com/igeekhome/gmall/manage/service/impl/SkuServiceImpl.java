package com.igeekhome.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.igeekhome.gmall.bean.*;
import com.igeekhome.gmall.manage.mapper.*;
import com.igeekhome.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.util.List;
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        int insert = pmsSkuInfoMapper.insert(pmsSkuInfo);


        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        //添加到平台属性值关联表
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insert(pmsSkuAttrValue);
        }
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insert(pmsSkuSaleAttrValue);
        }
        //图片
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());

            QueryWrapper<PmsProductImage> queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(PmsProductImage::getImgName,pmsSkuImage.getImgName());
            PmsProductImage pmsProductImage = pmsProductImageMapper.selectOne(queryWrapper);
            pmsSkuImage.setProductImgId(pmsProductImage.getId());
            pmsSkuImageMapper.insert(pmsSkuImage);
        }

return "success";
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId) {

        PmsSkuInfo pmsSkuInfo = pmsSkuInfoMapper.selectById(skuId);
        //封装图片
        QueryWrapper<PmsSkuImage> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsSkuImage::getSkuId,skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.selectList(queryWrapper);
        pmsSkuInfo.setSkuImageList(pmsSkuImages);


        QueryWrapper<PmsSkuAttrValue> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.lambda().eq(PmsSkuAttrValue::getSkuId,skuId);
        List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueMapper.selectList(queryWrapper2);
        pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValues);


        QueryWrapper<PmsSkuSaleAttrValue> queryWrapper3=new QueryWrapper<>();
        queryWrapper3.lambda().eq(PmsSkuSaleAttrValue::getSkuId,skuId);
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues = pmsSkuSaleAttrValueMapper.selectList(queryWrapper3);
        pmsSkuInfo.setSkuSaleAttrValueList(pmsSkuSaleAttrValues);
        return  pmsSkuInfo;
    }
}
