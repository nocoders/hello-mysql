package com.study.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shardingsphere.entity.Dict;
import com.study.shardingsphere.entity.Order;
import com.study.shardingsphere.entity.OrderItem;
import com.study.shardingsphere.entity.User;
import com.study.shardingsphere.mapper.DictMapper;
import com.study.shardingsphere.mapper.OrderItemMapper;
import com.study.shardingsphere.mapper.OrderMapper;
import com.study.shardingsphere.mapper.UserMapper;
import com.study.shardingsphere.vo.OrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linmeng
 * @date 2023/3/6 16:04
 */
@SpringBootTest
public class ShardingTest {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private DictMapper dictMapper;

	@Test
	// 垂直分片
	public void testInsertUserAndOrder() {
		User user = new User().setUname("哦爱抚");
		userMapper.insert(user);
		orderMapper.insert(new Order().setOrderNo("234").setUserId(user.getId()).setAmount(new BigDecimal(1000)));
	}

	@Test
	// 水平分片插入测试 指定 库和表，挨个测试能否插入成功
	public void testInsertOrder() {
		orderMapper.insert(new Order().setOrderNo("234").setUserId(1L).setAmount(new BigDecimal(1000)));
	}

	@Test
	// 水平分片插入测试 指定表，挨个测试能否插入成功
	public void testInsertOrderDatabaseStrategy() {
		for (long i = 0; i < 10; i++) {
			orderMapper.insert(new Order().setOrderNo("234").setUserId(i).setAmount(new BigDecimal(1000)));
		}
	}

	@Test
	// 水平分片插入测试 指定表，挨个测试能否插入成功
	public void testInsertOrderTableStrategy() {
		for (int i = 1; i < 10; i++) {
			orderMapper.insert(new Order().setOrderNo("order_no" + i).setUserId(i + 1L).setAmount(new BigDecimal(1000)));
			orderMapper.insert(new Order().setOrderNo("order_no" + i).setUserId((long) i).setAmount(new BigDecimal(1000)));
		}
	}

	@Test
	// 水平分片查询测试
	public void testShardingSelectAll() {
		QueryWrapper<Order> wrapper = new QueryWrapper<Order>().eq("user_id", 2L);
		orderMapper.selectList(wrapper);
	}

	@Test
	// 水平分片查询测试
	public void testShardingInsertOrderInfo() {
		for (int i = 1; i <= 6; i++) {
			String orderNo = "order_no" + i;
			orderMapper.insert(new Order().setOrderNo(orderNo).setUserId(1L).setAmount(new BigDecimal(1000)));
			for (int j = 0; j < 3; j++) {
				orderItemMapper.insert(new OrderItem().setOrderNo(orderNo).setCount(10).setUserId(1L).setPrice(BigDecimal.valueOf(100)));
			}
		}
		for (int i = 7; i <= 12; i++) {
			String orderNo = "order_no" + i;
			orderMapper.insert(new Order().setOrderNo(orderNo).setUserId(2L).setAmount(new BigDecimal(1000)));
			for (int j = 0; j < 3; j++) {
				orderItemMapper.insert(new OrderItem().setOrderNo(orderNo).setCount(10).setUserId(2L).setPrice(BigDecimal.valueOf(100)));
			}
		}
	}

	// 关联查询
	@Test
	public void testGetOrderAmount() {
		List<OrderVo> orderAmount = orderMapper.getOrderAmount();
		System.out.println(orderAmount.toString());
	}// 关联查询
	@Test
	// 广播表相关
	public void testBroadcast() {
		dictMapper.insert(new Dict().setDictType("cd"));
		dictMapper.selectList(null);
		dictMapper.selectList(null);
		dictMapper.selectList(null);
	}

}
