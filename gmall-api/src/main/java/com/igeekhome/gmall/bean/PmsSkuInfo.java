package com.igeekhome.gmall.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @param
 * @return
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PmsSkuInfo implements Serializable {

    @TableId(value="id",type= IdType.AUTO)
    String id;


    String productId;


    BigDecimal price;


    String skuName;


    BigDecimal weight;


    String skuDesc;


    String catalog3Id;


    String skuDefaultImg;
    @TableField(exist = false)
    String spuId;

    @TableField(exist = false)
    List<PmsSkuImage> skuImageList;

    @TableField(exist = false)
    List<PmsSkuAttrValue> skuAttrValueList;

    @TableField(exist = false)
    List<PmsSkuSaleAttrValue> skuSaleAttrValueList;



   }
