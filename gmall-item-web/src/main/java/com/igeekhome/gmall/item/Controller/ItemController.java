package com.igeekhome.gmall.item.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeekhome.gmall.bean.PmsProductInfo;
import com.igeekhome.gmall.bean.PmsProductSaleAttr;
import com.igeekhome.gmall.bean.PmsSkuInfo;
import com.igeekhome.gmall.service.SkuService;
import com.igeekhome.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {


    @Reference
    SkuService skuService;
    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public  String item(@PathVariable String skuId, ModelMap map, HttpServletRequest request){

        String remoteAddr = request.getRemoteAddr();
        request.getLocalAddr()
        PmsSkuInfo pmsSkuInfo =skuService.getSkuById(skuId,remoteAddr);

                map.put("skuInfo",pmsSkuInfo);
                //销售属性列表
       List<PmsProductSaleAttr> pmsProductSaleAttrs  =spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());
        map.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);
        return "item";
    }
}
