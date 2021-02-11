package com.huangcheng.community.community.interceptor;

import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.model.User;
import com.huangcheng.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 荒城
 * @title: SesstionInterceptor
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/108:06
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0)//判断cookies是否为空
        {
            for (Cookie cookie : cookies) {//取出cookie并进行比较
                if(cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);

                    if(users.size()!=0)
                    {
                        request.getSession().setAttribute("user",users.get(0));
                    }
                    break;

                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
