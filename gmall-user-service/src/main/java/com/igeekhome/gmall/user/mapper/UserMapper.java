package com.igeekhome.gmall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.igeekhome.gmall.bean.UmsMember;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UmsMember> {
     List<UmsMember> selectALLUser();
}