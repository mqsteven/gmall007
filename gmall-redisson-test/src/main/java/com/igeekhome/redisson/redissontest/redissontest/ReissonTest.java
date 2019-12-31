package com.igeekhome.redisson.redissontest.redissontest;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.igeekhome.gmall.util.RedisUtil;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@Controller
public class ReissonTest {


    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedissonClient redissonClient;


    @RequestMapping("redisTest")
    @ResponseBody
    public String index(){


        Jedis jedis = redisUtil.getJedis();
       RLock lock = redissonClient.getLock("anyLock");
        lock.lock();
        try {
            String y = jedis.get("y");

            if(y==null){
                jedis.set("y",1+"");

            }else{
                jedis.set("y",Integer.parseInt(y)+1+"");
                System.out.println(y);
            }
        }finally {
            lock.unlock();
        }


        jedis.close();
       // RLock lock = redissonClient.getLock("Lock");
        return "success";
    }
}
