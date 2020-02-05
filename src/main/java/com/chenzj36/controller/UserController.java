package com.chenzj36.controller;

import com.chenzj36.mapper.UserMapper;
import com.chenzj36.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    //选择全部用户
    @GetMapping("/selectUser")
    public List<User> selectUser(){

        List<User> users = userMapper.selectUser();
        return users;
    }

    //根据id选择用户
    @GetMapping("/selectUserById/{id}")
    public User selectUserById(@PathVariable("id") int id){
        User user = userMapper.selectUserById(id);
        System.out.println(user);
        return user;
    }
    //添加一个用户
    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(101,"chenzj","123456"));
        return "ok";
    }
    //修改一个用户
    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(101,"chenzj36","passwd"));
        return "ok";
    }
    //根据id删除用户
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userMapper.deleteUser(id);
        return "ok";
    }
}
