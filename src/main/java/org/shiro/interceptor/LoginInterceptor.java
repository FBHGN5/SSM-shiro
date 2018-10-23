package org.shiro.interceptor;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.shiro.dao.UserMapper;
import org.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
       @Autowired
       private UserMapper userMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

      //首先进入的方法
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        String url=request.getRequestURI();

        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipal()==null){
            return true;
        }
        String username=subject.getPrincipal().toString();
        //刷新session rememberMe账户直接登录
        if(user==null&&username!=null){
            user=userMapper.findByUsername(username);
            session.setAttribute("user",user);
        }
        System.out.println("username="+username);
        System.out.println("user{}="+user);
        System.out.println("url{}="+url);
        return true;

    }
    //返回modelAndView之前执行

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }
    //执行Handler完成执行此方法

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}