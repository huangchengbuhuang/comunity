package com.huangcheng.community.community.dto;

import com.huangcheng.community.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 荒城
 * @title: PagnationDto
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/819:09
 */
@Data
public class PagnationDto
{
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndpage;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalcount, Integer page, Integer size) {


        if(totalcount%size==0)
        {
            totalPage=totalcount/size;
        }else{
            totalPage=totalcount/size+1;
        }
        this.page=page;

        pages.add(page);
        for(int i=1;i<=3;i++)
        {
            if(page-i>0){
                pages.add(  0,page-i);
            }
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示上一页
        if(page==1)
        {
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //是否展示下一页
        if(page==totalPage)
        {
            showNext=false;
        }
        else {
            showNext=true;
        }
        //若包含第一页，那么去首页的选项就不展示出来
        if(pages.contains(1))
        {
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        //同样的，来判断最后一页
        if(pages.contains(totalPage))
        {
            showEndpage=false;
        }else{
            showEndpage=true;
        }

    }
}
