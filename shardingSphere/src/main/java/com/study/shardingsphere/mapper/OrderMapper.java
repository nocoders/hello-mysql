package com.study.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingsphere.entity.Order;
import com.study.shardingsphere.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author linmeng
 * @date 2023/3/6 21:29
 */

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

	// 数组可以换行
	@Select({"SELECT o.order_no, SUM(i.price * i.count) AS amount",
			"FROM tb_order o JOIN tb_order_item i ON o.order_no = i.order_no",
			"GROUP BY o.order_no"})
	List<OrderVo> getOrderAmount();

}
