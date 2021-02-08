package com.huangcheng.community.community.Mapper;

import com.huangcheng.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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

    @Select("select * from question")
    List<Question> list();
}
