package com.igeekhome.gmall.manage;


import com.igeekhome.gmall.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageServiceApplicationTests {


    @Test
    public void contextLoads() {
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.initPool("10.168.124.129",6379,0);
        Jedis jedis = redisUtil.getJedis();

        jedis.set("sku", "1101:jiwei");
        String test = jedis.get("sku");
        System.out.println("redis值："+test);
    }

}
