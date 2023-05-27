package com.qiqi.controller;


import com.qiqi.pojo.Photo;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.service.impl.PhotoServiceImpl;
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
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
     PhotoServiceImpl photoService;



    // 通过传进来的用户的class_id 查询该班级的相册

    @RequestMapping("getAllPhoto")
    public Result getAllPhoto(@RequestBody User user){
        return photoService.getAllPhoto(user);
    }

    // 向班级相册传入图片

    @RequestMapping("upLoadPhoto")
    public Result uploadPhoto(@RequestBody User user,@RequestParam String rote){
        return photoService.uploadPhoto(user,rote);
    }

    //删除班级相册中的图片
    public Result deletePhoto(@RequestBody Photo rote){
        return photoService.deletePhoto(rote);
    }

}

