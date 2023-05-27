package com.qiqi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiqi.mapper.UserMapper;
import com.qiqi.pojo.Grade;
import com.qiqi.mapper.GradeMapper;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.service.GradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiqi
 * @since 2023-05-27
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Autowired
    GradeMapper gradeMapper;

    @Autowired
    UserMapper userMapper;
    @Override
    public Result getGradeInfo(User user) {
        Grade grade = gradeMapper.selectById(user.getClassId());
        return Result.ok(grade);
    }
    @Override
    public Result gradeAll(Grade grade) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("class_id",grade.getId());
        List<User> users = userMapper.selectList(wrapper);
        return Result.ok(users);
    }

    @Override
    public Result addGrade(Grade grade) {
        int insert = gradeMapper.insert(grade);
        return insert == 1?Result.ok():Result.fail("创建失败");
    }
}
