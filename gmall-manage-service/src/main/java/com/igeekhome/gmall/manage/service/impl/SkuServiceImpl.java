package com.igeekhome.gmall.manage.service.impl;



import com.igeekhome.gmall.util.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.igeekhome.gmall.bean.*;
import com.igeekhome.gmall.manage.mapper.*;
import com.igeekhome.gmall.service.SkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    RedisUtil redisUtil;



    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        int insert = pmsSkuInfoMapper.insert(pmsSkuInfo);


        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        //添加到平台属性值关联表
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insert(pmsSkuAttrValue);
        }
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insert(pmsSkuSaleAttrValue);
        }
        //图片
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());

            QueryWrapper<PmsProductImage> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(PmsProductImage::getImgName, pmsSkuImage.getImgName());
            PmsProductImage pmsProductImage = pmsProductImageMapper.selectOne(queryWrapper);
            pmsSkuImage.setProductImgId(pmsProductImage.getId());
            pmsSkuImageMapper.insert(pmsSkuImage);
        }

        return "success";
    }


    @Override
    public PmsSkuInfo getSkuByIdDB(String skuId) {

        PmsSkuInfo pmsSkuInfo = pmsSkuInfoMapper.selectById(skuId);
        //封装图片
        QueryWrapper<PmsSkuImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsSkuImage::getSkuId, skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.selectList(queryWrapper);
        pmsSkuInfo.setSkuImageList(pmsSkuImages);


        QueryWrapper<PmsSkuAttrValue> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().eq(PmsSkuAttrValue::getSkuId, skuId);
        List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueMapper.selectList(queryWrapper2);
        pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValues);


        QueryWrapper<PmsSkuSaleAttrValue> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.lambda().eq(PmsSkuSaleAttrValue::getSkuId, skuId);
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues = pmsSkuSaleAttrValueMapper.selectList(queryWrapper3);
        pmsSkuInfo.setSkuSaleAttrValueList(pmsSkuSaleAttrValues);


        return pmsSkuInfo;

    }

    @Override
    public PmsSkuInfo getSkuById(String skuId ,String ip) {
        System.out.println(ip+Thread.currentThread().getName()+"该用户进入方法");
        Jedis jedis = redisUtil.getJedis();
        //查询缓存
        String s = jedis.get("PmsSkuInfo:" + skuId + ":info");
        if (StringUtils.isNotEmpty(s)) {
            JSONObject jsonObject = JSONObject.parseObject(s);
            PmsSkuInfo pmsSkuInfo = JSON.toJavaObject(jsonObject, PmsSkuInfo.class);
            jedis.close();
            return pmsSkuInfo;
        } else {
            //设置分布式锁
            String uuid = UUID.randomUUID().toString();
            String OK = jedis.set("PmsSkuInfo:" + skuId + ":lock", uuid, "nx", "px", 1000*600);
            if (StringUtils.isNotEmpty(OK) && OK.equals("OK")) {
                System.out.println(ip+Thread.currentThread().getName()+"该用户拿到了分布式锁十秒内操作");
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ip+Thread.currentThread().getName()+"该用户拿到了分布式锁三秒睡眠结束");

                //设置成功，有权在10秒的过期时间内访问数据库
                //缓存没有查询mysql
                PmsSkuInfo pmsSkuInfo = getSkuByIdDB(skuId);

                if (pmsSkuInfo != null) {

                    //mysql有存入缓存
                    jedis.set("PmsSkuInfo:" + skuId + ":info", JSON.toJSONString(pmsSkuInfo));


                } else {
                    //mysql没有 防止缓存穿透，null或者字符串存入redis
                    jedis.setex("PmsSkuInfo" + skuId + ":info", 60 * 3, JSON.toJSONString(""));


                }
                String value = jedis.get("PmsSkuInfo:" + skuId + ":lock");
               //  String script="if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
               // jedis.eval(script, Collections.singletonList("lock"),Collections.singletonList(value));
                if (StringUtils.isNotEmpty(value)&&uuid.equals(value)){
                    //jedis.eval("lua")可与用lua脚本，在查询到key的同时删除key，防止高并发的意外发生
                //访问完成释放分布式锁
                Long del = jedis.del("PmsSkuInfo:" + skuId + ":lock");
                }

                System.out.println("释放分布式锁");
                jedis.close();
                return pmsSkuInfo;
            } else {
                System.out.println(ip+Thread.currentThread().getName()+"该用户没有拿到分布式锁开始睡眠三秒钟");
                //设置失败，自旋
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ip+Thread.currentThread().getName()+"该用户没有拿到分布式锁睡眠三秒钟结束准备循环");
                return getSkuById(skuId,ip);

            }


        }


    }
}
