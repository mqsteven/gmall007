package com.igeekhome.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.igeekhome.gmall.bean.*;
import com.igeekhome.gmall.manage.mapper.PmsProductImageMapper;
import com.igeekhome.gmall.manage.mapper.PmsProductInfoMapper;
import com.igeekhome.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.igeekhome.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.igeekhome.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {


        QueryWrapper<PmsProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductInfo::getCatalog3Id, catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.selectList(queryWrapper);

        return pmsProductInfos;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        QueryWrapper<PmsProductSaleAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductSaleAttr::getProductId, spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.selectList(queryWrapper);
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrs) {
            QueryWrapper<PmsProductSaleAttrValue> queryWrapper2=new QueryWrapper<>();
            queryWrapper2.lambda().
                    eq(PmsProductSaleAttrValue::getProductId,spuId).
                    eq(PmsProductSaleAttrValue::getSaleAttrId,pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.selectList(queryWrapper2);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }

        return pmsProductSaleAttrs;
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {

        int insert = pmsProductInfoMapper.insert(pmsProductInfo);
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage:spuImageList) {
            pmsProductImage.setProductId(pmsProductInfo.getId());
            pmsProductImageMapper.insert(pmsProductImage);
        }

        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr:spuSaleAttrList) {
            pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrMapper.insert(pmsProductSaleAttr);
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue:spuSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrValueMapper.insert(pmsProductSaleAttrValue);
            }

        }
        return "success";
    }

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {


        QueryWrapper<PmsProductImage> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductImage::getProductId,spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.selectList(queryWrapper);

        return  pmsProductImages;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId) {


        QueryWrapper<PmsProductSaleAttr> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductSaleAttr::getProductId,productId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.selectList(queryWrapper);
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrs) {
            QueryWrapper<PmsProductSaleAttrValue> queryWrapper2=new QueryWrapper<>();
            queryWrapper2.lambda().eq(PmsProductSaleAttrValue::getProductId,productId).eq(PmsProductSaleAttrValue::getSaleAttrId,pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.selectList(queryWrapper2);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }
        return pmsProductSaleAttrs;
    }
}
