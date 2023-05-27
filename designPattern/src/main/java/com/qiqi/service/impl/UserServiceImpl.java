package com.qiqi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qiqi.pojo.Result;
import com.qiqi.pojo.User;
import com.qiqi.mapper.UserMapper;
import com.qiqi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;



    public static UserServiceImpl userService ;

    public static UserServiceImpl getIntence() {
        if (userService == null){
            userService = new UserServiceImpl();
        }
        return userService;

    }

    @Override
    public Result userLogin(User user) {
        if (StringUtils.isBlank(user.getUserName()) || user.getUserId() == null){
            return Result.fail("账号或密码不能为空");
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("user_name",user.getUserName());
        map.put("user_id",user.getUserId());
        List<User> users = userMapper.selectByMap(map);
        if (users == null){
            return Result.fail("账号或密码错误");
        }
        return Result.ok(users.get(0));
    }

    @Override
    public Result getDetail(User user) {

        userMapper.selectById(user.getId());
        return Result.ok(user);
    }

    @Override
    public Result updateMessage(User user) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",user.getAge()).set("sex",user.getSex()).eq("id",user.getId()).set("user_id",user.getUserId());
        // 传进来的是路径
        if (!StringUtils.isBlank(user.getHead())){
            wrapper.set("head",user.getHead());
        }
        int update = userMapper.update(user, wrapper);
        return update == 1 ? Result.ok("更新成功"):Result.fail("更新失败");
    }

    @Override
    public Result register(User user) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("user_id",user.getUserId());
        List<User> users = userMapper.selectByMap(map);
        if (users.size()>0){
            return Result.fail("学号重复");
        }
        userMapper.insert(user);
        return Result.ok();
    }
}
