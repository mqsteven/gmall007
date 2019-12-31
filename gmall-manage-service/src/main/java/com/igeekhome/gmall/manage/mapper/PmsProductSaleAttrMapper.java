package com.igeekhome.gmall.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.igeekhome.gmall.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductSaleAttrMapper extends BaseMapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectListByProductAndSkuId(@Param("productId") String productId,@Param("skuId") String skuId);
}
