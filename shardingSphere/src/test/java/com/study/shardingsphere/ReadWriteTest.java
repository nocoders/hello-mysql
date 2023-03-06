package com.study.shardingsphere;

import com.study.shardingsphere.entity.User;
import com.study.shardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author linmeng
 * @date 2023/3/6 16:04
 */
@SpringBootTest
public class ReadWriteTest {
	@Autowired
	private UserMapper userMapper;

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
	// 配置轮训负载均衡
	public void testLoadBalancer(){

		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
		userMapper.selectList(null);
	}
}
