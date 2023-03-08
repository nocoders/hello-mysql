package com.study.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingsphere.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author linmeng
 * @date 2023/3/8 02:03
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
}