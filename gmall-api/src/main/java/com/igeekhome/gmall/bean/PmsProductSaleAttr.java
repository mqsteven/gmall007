package com.igeekhome.gmall.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PmsProductSaleAttr implements Serializable {


    String id ;


    String productId;


    String saleAttrId;


    String saleAttrName;


    @TableField(exist = false)
    List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList;


}
