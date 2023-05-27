package com.qiqi.service;

import com.qiqi.pojo.Photo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiqi
 * @since 2023-05-27
 */
public interface PhotoService extends IService<Photo> {

    Result getAllPhoto(User user);

    Result uploadPhoto(User user, String rote);

    Result deletePhoto(Photo rote);
}
