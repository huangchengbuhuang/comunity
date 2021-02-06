package com.huangcheng.community.community.Mapper;

import com.huangcheng.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 荒城
 * @title: UserMapper
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/515:21
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_Creat,gmt_Modified) " +
            "values(#{account_id},#{name},#{token},#{gmt_Creat},#{gmt_Modified})")
    void insert(User user);

}
