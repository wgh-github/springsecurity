package com.lx.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.lx.springsecurity.entity.User user = userService.getOne(new QueryWrapper<com.lx.springsecurity.entity.User>().eq(com.lx.springsecurity.entity.User.USERNAME, username));
        System.out.println(user);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        //username  是前端传递的参数用户名
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_sale,ROLE_manager");
        return new User(user.getUsername(),bCryptPasswordEncoder.encode(user.getPassword()),auths);
    }

}
