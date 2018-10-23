package org.shiro.web;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.shiro.dao.UserMapper;
import org.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
   @Autowired
   private UserMapper userMapper;
    @RequestMapping("/login")
    public String login(HttpSession session) {

        return "login";
    }

    @RequestMapping(value = "/subLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(User user, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            subject.login(token);


        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        user=userMapper.findByUsername(user.getUsername());
        session.setAttribute("user",user);
        if (subject.hasRole("admin")) {
            return "有admin权限";
        }

        return "登录成功";
    }

    @RequiresRoles(value = {"admin", "manager", "user"}, logical = Logical.OR)
    @RequestMapping(value = "/testRole", method = RequestMethod.GET)
    @ResponseBody
    public String testRole(HttpSession session) {

        return "test role success";
    }
    @RequiresRoles("s")
    @RequestMapping(value = "/testRole1", method = RequestMethod.GET)
    @ResponseBody
    public String testRole1() {
        return "test role success";
    }

    @RequiresPermissions("list")
    @RequestMapping(value = "/testPerms", method = RequestMethod.GET)
    @ResponseBody
    public String testPerms() {
        return "test roles success";
    }


    @RequestMapping(value = "/testPerms1", method = RequestMethod.GET)
    @ResponseBody
    public String testPerms1() {
        return "test roles1 success";
    }

    @RequestMapping(value = "/testPerms2", method = RequestMethod.GET)
    @ResponseBody
    public String testPerms2() {
        return "test roles2 success";
    }
}
