package com.qiqi.service.impl;

import com.qiqi.pojo.Photo;
import com.qiqi.mapper.PhotoMapper;
import com.qiqi.service.PhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
