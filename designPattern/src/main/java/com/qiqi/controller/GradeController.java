package com.qiqi.controller;


import com.qiqi.pojo.Grade;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.service.impl.GradeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qiqi
 * @since 2023-05-27
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    GradeServiceImpl gradeService;

    //获取班级信息

    @RequestMapping("getGradeInfo")
    public Result getGradeInfo(@RequestBody User user){
        return gradeService.getGradeInfo(user);
    }

    //班级的全部同学

    @RequestMapping("gradeAll")
    public Result gradeAll(@RequestBody Grade grade){
        return gradeService.gradeAll(grade);
    }

    //添加班级

    @RequestMapping("addGrade")
    public Result addGrade(@RequestBody Grade grade){
        return gradeService.addGrade(grade);
    }

}

