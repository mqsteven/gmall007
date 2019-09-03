package com.igeekhome.gmall.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value="id",type= IdType.AUTO)
    String id ;


    String productId;


    String saleAttrId;


    String saleAttrValueName;

    @TableField(exist = false)
    String isChecked;

 }
