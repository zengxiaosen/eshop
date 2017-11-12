package com.z.eshop.web.controller;

import com.z.eshop.model.User;
import com.z.eshop.service.UserService;
import com.z.eshop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.SessionScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by root on 17-11-12.
 */
@Controller
public class UserController {
    //注入UserService给Controller
    @Resource(name = "userService")
    private UserService us;

    /**
     * 去注册页面
     * @return
     */
    @RequestMapping(value = "/toRegPage", method = RequestMethod.GET)
    public String toRegPage(){
        return "userReg";
    }

    /**
     * 完成注册
     * @return
     */
    @RequestMapping(value = "/doReg", method = RequestMethod.POST)
    public String doReg(User user, HttpServletRequest req, Model m){
        //不要忘了，还有确认密码。。
        //ServletWebRequest r
        //得到确认密码
        String confirmPass = req.getParameter("confirmPass");
        //1.判断密码是否一致
        if(!user.getPassword().equals(confirmPass)){
            m.addAttribute("error", "两次密码输入不一致，确认后请重新输入！！");
            return "userReg";
        }
        //2.判断email是否唯一

        //保存用户
        us.saveEntity(user);
        System.out.println(" 注册成功!");
        return "login";
    }

    /**
     * 去登录页面
     * @return
     */
    @RequestMapping(value = "/toLoginPage", method = RequestMethod.GET)
    public String toLoginPage(){
        return "login";
    }

    //向客户端回传数据，一般是给他一个model

    /**
     *
     * @param user  客户端提交的用户信息
     * @param s session对象， 保存登录成功的用户名
     * @param m 登录失败向客户端回传失败信息的一个消息载体
     * @return
     */
    //model 就是在请求里放数据
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(User user, HttpSession s, Model m){
        String hql = "from User u where u.name = ? and u.password = ?";
        List<User> list =  us.findByHQL(hql, user.getName(), user.getPassword());
        if(list == null || list.isEmpty()){
            System.out.println("失败");
            m.addAttribute("error", "用户名/密码验证失败, 请重试");
        }else{
            //验证成功，将用户名信息保存到session中
            System.out.println("验证成功");
            User u = list.get(0);
            s.setAttribute("name", user.getName());

        }
        return "index";
    }



}
