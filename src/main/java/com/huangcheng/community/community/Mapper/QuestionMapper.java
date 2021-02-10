package com.huangcheng.community.community.Mapper;

import com.huangcheng.community.community.dto.QuestionDto;
import com.huangcheng.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 荒城
 * @title: QuestionMapper
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/716:16
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag)" +
            " values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
     void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value="size") Integer size);
    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset, @Param(value="size") Integer size);
    @Select("select count(1) from question where creator=#{userId}")
    Integer countuserById(@Param(value = "userId") Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(@Param(value = "id") Integer id);
}
