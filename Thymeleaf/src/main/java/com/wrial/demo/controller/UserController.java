package com.wrial.demo.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) &&
                username.equals("wrial") && password.equals("123")) {
            map.put("msg", "登陆成功！");
            //配置拦截器进行安全检测
            session.setAttribute("userKey", username);
            //为了防止重复提交请求，使用重定向
            return "redirect:/main.html";
        } else {
            map.put("msg", "登陆失败，请重试");
            return "login";
        }
    }
}
