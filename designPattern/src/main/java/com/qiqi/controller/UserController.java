package com.qiqi.controller;


import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.service.impl.UserServiceImpl;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService ;

    @RequestMapping("hello")
    public String test(){
        return "hello";
    }


    // 用户登陆接口
    @RequestMapping("userLogin")
    public Result userLogin(@RequestBody User user){
        return userService.userLogin(user);
    }

    //获取详细信息
    @RequestMapping("getDetail")
    public Result getDetail(@RequestBody User user){
        return userService.getDetail(user);
    }
    //更新信息

    @RequestMapping("updateMessage")
    public Result updateMessage(@RequestBody User user){
        return userService.updateMessage(user);
    }

    @RequestMapping("register")
    public Result register(@RequestBody User user){
        return userService.register(user);
    }
}

