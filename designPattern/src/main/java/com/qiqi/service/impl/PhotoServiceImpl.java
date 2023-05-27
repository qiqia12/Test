package com.qiqi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qiqi.pojo.Photo;
import com.qiqi.mapper.PhotoMapper;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.service.PhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
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
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Autowired
    PhotoMapper photoMapper;



    public static PhotoServiceImpl photoService;

    public static PhotoServiceImpl getInstance(){
        if (photoService == null){
            synchronized (PhotoServiceImpl.class){
                if (photoService == null){
                    photoService = new PhotoServiceImpl();
                }
            }
        }
        return photoService;
    }




    @Override
    public Result getAllPhoto(User user) {
        int classId = user.getClassId();
        HashMap<String,Object> map = new HashMap<>();
        map.put("class_id",classId);
        map.put("delete_flage",0);
        List<Photo> photos = photoMapper.selectByMap(map);
        return Result.ok(photos);
    }

    @Override
    public Result uploadPhoto(User user, String rote) {
        Photo photo = new Photo();
        photo.setCreateTime(Date.valueOf(LocalDate.now()));
        photo.setClassId(user.getClassId());
        photo.setType(rote);
        int insert = photoMapper.insert(photo);
        return insert ==1 ? Result.ok():Result.fail("上传失败");
    }

    @Override
    public Result deletePhoto(Photo rote) {
        UpdateWrapper<Photo> wrapper = new UpdateWrapper<>();
        wrapper.set("delete_flage",1).eq("type",rote.getType());
        wrapper.eq("id",rote.getId());
        rote.setDeleteFlage(null);
        int update = photoMapper.update(rote, wrapper);
        return update == 1 ? Result.ok():Result.fail("删除失败");
    }
}
