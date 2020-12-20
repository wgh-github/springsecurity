package com.lx.springsecurity.service.impl;

import com.lx.springsecurity.entity.User;
import com.lx.springsecurity.mapper.UserMapper;
import com.lx.springsecurity.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wgh
 * @since 2020-12-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
