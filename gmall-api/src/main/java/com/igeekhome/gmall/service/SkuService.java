package com.igeekhome.gmall.service;

import com.igeekhome.gmall.bean.PmsSkuInfo;

public interface SkuService {
    String  saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuByIdDB(String skuId);

    PmsSkuInfo getSkuById(String skuId,String ip);
}
