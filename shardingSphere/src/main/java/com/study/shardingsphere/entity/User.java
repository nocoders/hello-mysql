package com.study.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author linmeng
 * @date 2023/3/6 15:00
 */

@TableName("tb_user")
@Data
@Accessors(chain = true)
public class User {
	@TableId(type = IdType.AUTO)
	private Long id;
	private String uname;
}
