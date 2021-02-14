package com.huangcheng.community.community.service;

import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.model.User;
import com.huangcheng.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 荒城
 * @title: UserService
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1014:55
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrupdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);

        if(users.size() == 0){
            //插入
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());

            userMapper.insert(user);
        }else{
            //更新

            User dbuser = users.get(0);
            User updateuser = new User();
            updateuser.setGmtModified(System.currentTimeMillis());
            updateuser.setName(user.getName());
            updateuser.setAvatarUrl(user.getAvatarUrl());
            updateuser.setToken(user.getToken());

            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateuser, example);

        }
    }
}
