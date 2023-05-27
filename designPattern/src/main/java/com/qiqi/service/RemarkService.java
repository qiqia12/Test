package com.qiqi.service;

import com.qiqi.pojo.Remark;
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
public interface RemarkService extends IService<Remark> {

    Result getAllRemark(User user);

    Result giveItLike(Remark remark);

    Result leaveRemark(Remark remark);
}
