package com.igeekhome.gmall.bean;

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


    private String id;

    private String productId;

    private String imgName;

    private String imgUrl;


}