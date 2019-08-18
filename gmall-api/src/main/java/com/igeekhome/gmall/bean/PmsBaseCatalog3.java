package com.igeekhome.gmall.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PmsBaseCatalog3 implements Serializable {

    private String  id;
    private String  name;
    private String  catakog2Id;
}
