package xyz.shi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import xyz.shi.dao.UserDao;
import xyz.shi.domin.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("username")
@RequestMapping("/home")
public class LoginController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/userlogin")
    public String userlogin() {
        return "userlogin";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,Model model,HttpServletRequest request) {
        User user1 = new User();
        user1.setName(username);
        user1.setPassword(password);
        boolean flag = userDao.login(user1);
        if (!flag) {
            model.addAttribute("error", "用户名或密码错误");
            System.out.println("用户名或密码错误");
            // 页面路径
//            return "/WEB-INF/view/user/add.jsp";
            return "userlogin";
        }
//        model.addAttribute("username", username);
        //将用户信息放入 session 中
        request.getSession(true).setAttribute("username",username);
        System.out.println("登录成功"+ username);

        return "redirect:/users/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,SessionStatus sessionStatus) {
        session.removeAttribute("username");
        sessionStatus.setComplete();
        System.out.println("注销成功");
        System.out.println(session.getAttribute("username"));
        return "redirect:userlogin";
    }
}
