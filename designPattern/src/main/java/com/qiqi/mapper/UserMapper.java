package com.qiqi.mapper;

import com.qiqi.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiqi
 * @since 2023-05-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
