package com.study.shardingsphere;

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
public class ReadWriteTest {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void test(){
		User user = new User().setUname("小明");
		userMapper.insert(user);
	}
	@Test
	@Transactional
	// 测试方法 直接回滚，添加事务后，添加及查询均使用主库master
	public void testRollBack(){
		User user = new User().setUname("小明2");
		userMapper.insert(user);
		userMapper.selectList(null);
	}

	@Test
	// 负载均衡
	public void testLoadBalancer(){

		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
	}
	@Test
	// 垂直分片
	public void testInsertUserAndOrder() {
		User user = new User().setUname("哦爱抚");
		userMapper.insert(user);
		orderMapper.insert(new Order().setOrderNo("234").setUserId(user.getId()).setAmount(new BigDecimal(1000)));
	}
}
