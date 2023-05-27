package com.qiqi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qiqi.pojo.Remark;
import com.qiqi.mapper.RemarkMapper;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.service.RemarkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
public class RemarkServiceImpl extends ServiceImpl<RemarkMapper, Remark> implements RemarkService {

    public static RemarkServiceImpl remarkService = new RemarkServiceImpl();

    public static  RemarkServiceImpl getInstance(){
        return remarkService;
    }

    @Autowired
    RemarkMapper remarkMapper;
    @Override
    public Result getAllRemark(@RequestBody User user) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("class_id",user.getClassId());
        List<Remark> remarks = remarkMapper.selectByMap(map);
        return Result.ok(remarks);
    }

    @Override
    public Result giveItLike(@RequestBody Remark remark) {
        remark = remarkMapper.selectById(remark.getId());
        UpdateWrapper<Remark> wrapper = new UpdateWrapper<>();
        wrapper.set("favor",remark.getFavor()+1);
        remark.setFavor(null);
        wrapper.eq("id",remark.getId());
        remarkMapper.update(remark,wrapper);
        return Result.ok();
    }

    @Override
    public Result leaveRemark(@RequestBody Remark remark) {
        remarkMapper.insert(remark);
        return Result.ok();
    }
}
