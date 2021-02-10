package com.huangcheng.community.community.service;

import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User dbuser=userMapper.findByAccountId(user.getAccount_id());
        if(dbuser == null){
            //插入
            user.setGmt_Creat(System.currentTimeMillis());
            user.setGmt_Modified(user.getGmt_Creat());
            userMapper.insert(user);
        }else{
            //更新
            dbuser.setGmt_Creat(System.currentTimeMillis());
            dbuser.setName(user.getName());
            dbuser.setAvatar_url(user.getAvatar_url());
            dbuser.setToken(user.getToken());

            userMapper.update(dbuser);
        }
    }
}
