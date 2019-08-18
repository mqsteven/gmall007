package com.igeekhome.gmall.user;

import com.alibaba.fastjson.JSON;
import com.igeekhome.gmall.bean.UmsMember;
import com.igeekhome.gmall.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallUserApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void contextLoads() {
        List<UmsMember> all = userMapper.selectList(null);
        System.out.println(JSON.toJSON(all));
    }

}
