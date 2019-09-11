package com.igeekhome.gmall.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @param
 * @return
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PmsSkuSaleAttrValue implements Serializable {

    @TableId(value="id",type= IdType.AUTO)
    String id;


    String skuId;


    String saleAttrId;


    String saleAttrValueId;


    String saleAttrName;


    String saleAttrValueName;

}
