package com.igeekhome.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PmsProductSaleAttrValue implements Serializable {

    String id ;


    String productId;


    String saleAttrId;


    String saleAttrValueName;

    @TableField(exist = false)
    String isChecked;

 }
