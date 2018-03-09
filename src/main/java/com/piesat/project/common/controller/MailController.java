package com.piesat.project.common.controller;

import com.piesat.project.common.mail.MailHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 邮件发送接收
 * </p>
 *
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/sys/mail")
public class MailController extends com.piesat.project.common.base.BaseController {

    @Autowired
    MailHelper mMailHelper;

    @PostMapping("/send")
    public String send(Model model, String email) {
        model.addAttribute("email", email);
        model.addAttribute("loginName", "");
        boolean rlt = mMailHelper.sendMail(email, "SpringWind 测试邮件！", "/mail/tplSend.html", model);
        String tipMsg = "发送邮件至【" + email + "】失败！！";
        if (rlt) {
            tipMsg = "已成功发送邮件至【" + email + "】注意查收！！";
        }
        model.addAttribute("tipMsg", tipMsg);
        return "/mail/send";
    }

}
