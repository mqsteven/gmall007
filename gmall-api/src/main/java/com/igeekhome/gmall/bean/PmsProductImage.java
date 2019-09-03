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
public class PmsProductImage implements Serializable {

    @TableId(value="id",type= IdType.AUTO)
    private String id;

    private String productId;

    private String imgName;

    private String imgUrl;


}