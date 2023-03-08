package com.study.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author linmeng
 * @date 2023/3/8 00:33
 */

@TableName("tb_order_item")
@Data
@Accessors(chain = true)
public class OrderItem {
	//当配置了shardingsphere-jdbc的分布式序列时，自动使用shardingsphere-jdbc的分布式序列
	@TableId(type = IdType.AUTO)
	private Long id;
	private String orderNo;
	private Long userId;
	private BigDecimal price;
	private Integer count;
}