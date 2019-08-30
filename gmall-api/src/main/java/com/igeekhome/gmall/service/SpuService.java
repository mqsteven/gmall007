package com.igeekhome.gmall.service;

import com.igeekhome.gmall.bean.PmsBaseAttrInfo;
import com.igeekhome.gmall.bean.PmsProductInfo;
import com.igeekhome.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    List<PmsProductSaleAttr> spuSaleAttrList(String productId);
}
