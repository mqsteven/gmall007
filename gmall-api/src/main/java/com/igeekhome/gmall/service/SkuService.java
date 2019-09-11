package com.igeekhome.gmall.service;

import com.igeekhome.gmall.bean.PmsSkuInfo;

public interface SkuService {
    String  saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId);
}
