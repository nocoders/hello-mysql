package com.study.shardingsphere.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author linmeng
 * @date 2023/3/8 01:48
 */

@Data
public class OrderVo {
	private String orderNo;
	private BigDecimal amount;
}
