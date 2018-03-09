package com.piesat.project.controller;

import com.piesat.project.common.Result;
import com.piesat.project.common.utils.ShiroUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class UserController {

    @GetMapping("/login")
    String login() {
        return "html/login";
    }

    @PostMapping("/login")
    @ResponseBody
    Result ajaxLogin(String username, String password) {
        try {
//            password = MD5Utils.encrypt(username, password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();

            subject.login(token);
            return Result.success();
        } catch (AuthenticationException e) {
            return Result.error("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login.html";
    }

    @GetMapping("/position")
    String position() {
        return "html/shadow/position";
    }
}
