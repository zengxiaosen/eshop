package com.z.eshop.web.controller;

/**
 * Created by root on 17-11-13.
 */

import com.z.eshop.model.User;
import com.z.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员控制器
 */
@Controller
public class AdminController {
    @Resource
    private UserService us;

    /**
     * 查看用户列表
     * @return
     */
    @RequestMapping(value = "/admin/userList", method = RequestMethod.GET)
    public String userList(Model m){
        List<User> allUsers = us.findAllEntities();
        m.addAttribute("allUsers", allUsers);
        return "userList";
    }

    @RequestMapping(value = "/admin/delUser", method = RequestMethod.GET)
    public String delUser(@RequestParam("uid") int uid){
        User u = new User();
        u.setId(uid);
        us.deleteEntity(u);
        return "redirect:/admin/userList";
    }
}
