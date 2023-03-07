package com.study.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shardingsphere.entity.Order;
import com.study.shardingsphere.entity.User;
import com.study.shardingsphere.mapper.OrderMapper;
import com.study.shardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
			orderMapper.insert(new Order().setOrderNo("order_no"+i).setUserId(i+ 1L).setAmount(new BigDecimal(1000)));
			orderMapper.insert(new Order().setOrderNo("order_no"+i).setUserId((long) i).setAmount(new BigDecimal(1000)));
		}
	}
	@Test
	// 水平分片查询测试
	public void testShardingSelectAll() {
		QueryWrapper<Order> wrapper = new QueryWrapper<Order>().eq("user_id", 2L);
		orderMapper.selectList(wrapper);
	}
}
