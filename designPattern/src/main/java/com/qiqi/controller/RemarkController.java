package com.qiqi.controller;


import com.qiqi.pojo.Remark;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.service.impl.RemarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qiqi
 * @since 2023-05-27
 */
@RestController
@RequestMapping("/remark")
public class RemarkController {

    @Autowired
    RemarkServiceImpl remarkService;
    //获取当前用户班级的留言板
    @RequestMapping("getAllRemark")
    public Result getAllRemark(@RequestBody User user){
        return remarkService.getAllRemark(user);
    }

    @RequestMapping("giveItLike")
    public Result giveItLike(@RequestBody Remark remark){
        return remarkService.giveItLike(remark);
    }

    @RequestMapping("leaveRemark")
    public Result leaveRemark(@RequestBody Remark remark){
        return remarkService.leaveRemark(remark);
    }

}

