package com.lx.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController {
    /*
      配置用户名密码
        1.配置文件spring.security.user.name=wgh
                 spring.security.user.password=123456
        2.通过配置类
          SecurityConfig
        3.自定编写
     */

    /**
     *  默认登录用户user/控制台密码
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "欢迎来到首页";
    }
}
