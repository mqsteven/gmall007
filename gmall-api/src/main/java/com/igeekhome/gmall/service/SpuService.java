package com.igeekhome.gmall.service;

import com.igeekhome.gmall.bean.PmsBaseAttrInfo;
import com.igeekhome.gmall.bean.PmsProductInfo;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);
}
