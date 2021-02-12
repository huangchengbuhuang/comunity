package com.huangcheng.community.community.Mapper;

import com.huangcheng.community.community.model.Question;
import com.huangcheng.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
}