package com.igeekhome.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PmsProductInfo implements Serializable {


    private String id;


    private String productName;


    private String description;


    private  String catalog3Id;

    @TableField(exist = false)
    private List<PmsProductSaleAttr> pmsProductSaleAttrList;
    @TableField(exist = false)
    private List<PmsProductImage> pmsProductImageList;



}


