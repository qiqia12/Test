package com.qiqi.service;

import com.qiqi.pojo.Grade;
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
public interface GradeService extends IService<Grade> {

    Result getGradeInfo(User user);

    Result gradeAll(Grade grade);

    Result addGrade(Grade grade);
}
