package com.study.shardingsphere.controller;

import com.study.shardingsphere.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linmeng
 * @date 2023/3/6 17:13
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/get")
	public String userSelect(){
		return userMapper.selectList(null).toString();
	}
}
