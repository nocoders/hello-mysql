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
//	@Transactional
	//读写分离测试  事务测试 添加事务后，测试方法执行完成后直接回滚不往数据库插入数据，直接回滚，
	// 并且事务添加后 数据添加及查询均使用主库master，不再读写分离
	public void testRollBack(){
		User user = new User().setUname("小明2");
		userMapper.insert(user);
		userMapper.selectList(null);
	}

	@Test
	// 读写分离 负载均衡
	public void testLoadBalancer(){

		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
	}
}
