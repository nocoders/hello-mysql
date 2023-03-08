package com.study.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingsphere.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author linmeng
 * @date 2023/3/8 00:34
 */

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}