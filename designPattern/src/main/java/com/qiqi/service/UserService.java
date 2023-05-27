package com.qiqi.service;

import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiqi
 * @since 2023-05-27
 */
public interface UserService extends IService<User> {

    Result userLogin(User user);

    Result getDetail(User user);

    Result updateMessage(User user);

    Result register(User user);
}
