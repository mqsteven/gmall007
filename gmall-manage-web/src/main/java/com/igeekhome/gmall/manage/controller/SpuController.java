package com.igeekhome.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeekhome.gmall.bean.PmsProductImage;
import com.igeekhome.gmall.bean.PmsProductInfo;
import com.igeekhome.gmall.bean.PmsProductSaleAttr;
import com.igeekhome.gmall.service.SpuService;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
public class SpuController {
    @Value("${fileServer.url}")
    String fileUrl;

    @Reference
    SpuService spuService;

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId){
        List<PmsProductImage> pmsProductImages=spuService.spuImageList(spuId);
        return pmsProductImages;
    }



    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        String success=spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }

     @RequestMapping("fileUpload")
     @ResponseBody
    public String fileUpload(@RequestParam("file")MultipartFile file) throws IOException, MyException {
    //将图片或者音频上传到分布式的文件存储系统
         String imgUrl="http://"+fileUrl;
         if(file!=null){
             System.out.println("multipartFile = " + file.getName()+"|"+file.getSize());

             String configFile = this.getClass().getResource("/tracker.conf").getFile();
             ClientGlobal.init(configFile);
             TrackerClient trackerClient=new TrackerClient();
             TrackerServer trackerServer=trackerClient.getConnection();
             StorageClient storageClient=new StorageClient(trackerServer,null);
             String filename=file.getOriginalFilename();
             String extName = StringUtils.substringAfterLast(filename, ".");

             String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
             imgUrl="http://"+fileUrl ;
             for (int i = 0; i < upload_file.length; i++) {
                 String path = upload_file[i];
                 imgUrl+="/"+path;
             }

         }
    //将图片的存储路径返回给页面
        return imgUrl;
    }


    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo>  pmsProductInfos=spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }
    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        List<PmsProductSaleAttr>  pmsProductSaleAttrs=spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

}
