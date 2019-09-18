package com.yy.blog.controller.admin;

import com.yy.blog.domian.User;
import com.yy.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 登录页
     * @return
     */
    @RequestMapping("/")
    public String loginrouter(){
        return "admin/login";
    }

    /**
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public String indexrouter(){
        return "admin/index";
    }

    /**
     * 登录功能
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request, Model model){
        User user = loginService.login(username, password);
        if (user == null){
            model.addAttribute("message","用户名或者密码错误");
            return "admin/login";
        }
        request.getSession().setAttribute("user",user);
        /*return "redirect:/admin/index";*/
        return "admin/index";
    }

    /**
     * 退出功能
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "admin/login";
    }


}
