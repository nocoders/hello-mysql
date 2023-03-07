package com.study.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author linmeng
 * @date 2023/3/6 21:27
 */

@TableName("tb_order")
@Data
@Accessors(chain = true)
public class Order {
	@TableId(type = IdType.ID_WORKER)
//	@TableId(type = IdType.AUTO)
	private Long id;
	private String orderNo;
	private Long userId;
	private BigDecimal amount;
}
