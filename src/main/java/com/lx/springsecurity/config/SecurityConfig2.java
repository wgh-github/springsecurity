package com.lx.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 3.自定义配置用户名密码
 */
@Configuration
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    //配置自动登录数据源
    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        //配置自动登录需要查询数据库连接
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);//创建自动登录tooken表
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.logout().logoutUrl("/user/logout");//配置登录退出
        http.exceptionHandling().accessDeniedPage("/403.html");//配置没有权限跳转的页面
        http.formLogin()//自定义编写登录页面
                .loginPage("/user/toLogin")//登录页面
                .loginProcessingUrl("/user/login")//登录访问路径
                .defaultSuccessUrl("/index").permitAll()//登录成功后跳转的路劲
                .and()
                .authorizeRequests().antMatchers("/user/toLogin","/user/hello","/user/gun","/updateUser.html").permitAll()//设置哪些路径不需要认证
                //.antMatchers("/user/test").hasAuthority("admin")//设置/user/test路径需要admin权限
                //.antMatchers("/user/test").hasAnyAuthority("manager,admin")//设置/user/test路径配置多个权限
                //.antMatchers("/user/test").hasRole("sale")//设置/user/test路径配置角色
                .antMatchers("/user/test").hasAnyRole("sale,manager")//设置/user/test路径配置角色
                .anyRequest().authenticated()  //所有请求需要验证
                .and()
                //设置自动登录
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(120)//设置登录时长 秒
                .userDetailsService(userDetailsService)
                .and();
                //csrf 防止跨站请求伪造 只针对put,delete,post请求保护
                //.csrf().disable();//关闭csrf防护，默认开启
    }
}
