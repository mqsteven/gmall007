package com.atguigu.gmall.user.mapper;

import com.igeekhome.gmall.bean.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UmsMember> {
     List<UmsMember> selectALLUser();
}