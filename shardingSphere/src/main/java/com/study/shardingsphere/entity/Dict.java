package com.study.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author linmeng
 * @date 2023/3/8 02:02
 */

@TableName("tb_dict")
@Data
@Accessors(chain = true)
public class Dict {
	//可以使用MyBatisPlus的雪花算法
	@TableId(type = IdType.ID_WORKER)
	private Long id;
	private String dictType;
}