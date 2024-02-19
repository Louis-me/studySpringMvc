package xyz.shi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.shi.dao.UserDao;
import xyz.shi.domin.User;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao userDao;


    @GetMapping("/list")
    public String list(Model model) {

        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "userList";
    }
    @GetMapping("/addPage")
    public String addPage(Model model) {
        return "useradd";
    }
    @PostMapping("/add")
    public String add(@RequestParam String username, @RequestParam String password, Model model) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        userDao.save(user);
        System.out.println("添加用户信息成功");
        return "redirect:/users/list";
    }
    @GetMapping("/findPage")
    public String findPage(@RequestParam Integer id ,Model model) {
        User user = userDao.findById(id);
        if (user!=null) {
            model.addAttribute("user", user);
        } else {
            System.out.println("用户信息不存在");
        }
        return "useredit";
    }
    @PostMapping("/update")
    public String update(@RequestParam Integer id ,@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setName(username);
        userDao.update(user);
        System.out.println("更新成功");
        return "redirect:/users/list";
    }
    @GetMapping("/del")
    public String del(@RequestParam Integer id) {
        userDao.delete(id);
        System.out.println("删除成功");
        return "redirect:/users/list";

    }
}
