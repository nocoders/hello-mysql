package com.study.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingsphere.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author linmeng
 * @date 2023/3/6 15:01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
