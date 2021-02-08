package com.huangcheng.community.community.Mapper;

import com.huangcheng.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    @Insert("insert into user (account_id,name,token,gmt_Creat,gmt_Modified,avatar_url) " +
            "values(#{account_id},#{name},#{token},#{gmt_Creat},#{gmt_Modified},#{avatar_url})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id")Integer id);
}
