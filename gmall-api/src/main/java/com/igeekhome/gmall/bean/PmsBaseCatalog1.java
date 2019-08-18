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
public class PmsBaseCatalog1 implements Serializable {

    private String id;

    private String name;

    @TableField(exist = false)
    private List<PmsBaseCatalog2> catalog2s;

}

