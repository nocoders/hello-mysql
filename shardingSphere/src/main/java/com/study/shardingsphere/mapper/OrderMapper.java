package com.study.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingsphere.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author linmeng
 * @date 2023/3/6 21:29
 */

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
