package com.lx.springsecurity.controller;


import com.lx.springsecurity.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wgh
 * @since 2020-12-15
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/gun")
    @ResponseBody
    public String gun() {
        return "gun";
    }
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    //@Secured({"ROLE_sale","manager"})//配置角色
    //@PreAuthorize("hasAuthority('admin')")
    //@PreAuthorize("hasRole('admin')")
    //@PostAuthorize("hasRole('admins')")//在方法返回之后生效
    @GetMapping("/add")
    @ResponseBody
    public String add() {
        System.out.println("====放");
        return "add";
    }

    //对请求参数过滤
    //@PostFilter("filterObject.username=='王五'")返回王五
    //@PreFilter(value = "filterObject.userId>5")对传入数据过滤
    @GetMapping("/get")
    @ResponseBody
    public List<User> getAllUser(User id){
        System.out.println(id);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("王五","1231"));
        users.add(new User("王五2","123231"));
        return users;
    }



}
