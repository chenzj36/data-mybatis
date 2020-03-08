# SpringBoot整合Mybatis


###### 原文链接：https://www.cnblogs.com/hellokuangshen/p/11331338.html
#### 导入依赖    
```
<dependency>
	<groupId>org.mybatis.spring.boot</groupId>
	<artifactId>mybatis-spring-boot-starter</artifactId>
	<version>2.1.0</version>
</dependency>
```
#### 配置数据库连接信息    
```
spring.datasource.username=root
spring.datasource.password=chen
spring.datasource.url=jdbc:mysql://localhost:3306?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
#### 测试连接        
![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580824572626.png)        
![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580824626143.png)        
#### 创建实体类        
```java
package com.chenzj36.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
}
```
#### 配置Mapper接口类    
```java
package com.chenzj36.mapper;

import com.chenzj36.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    List<User> selectUser();
    User selectUserById(int id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
```
#### Mapper映射文件    
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--mapper namespace填写接口类的完整路径-->
<mapper namespace="com.chenzj36.mapper.UserMapper">

<!--id对应接口类中的方法名，resultType对应返回值中封装的基本类型-->
    <select id="selectUser" resultType="User">
    select * from mybatis.user
  </select>

    <select id="selectUserById" resultType="User">
    select * from mybatis.user where id = #{id}
</select>
    
<!--parameterType对应传入的基本数据类型，sql语句直接写属性即可#{属性值}-->
    <insert id="addUser" parameterType="User">
    insert into mybatis.user (id,username,password) values (#{id},#{username},#{password})
</insert>
    <update id="updateUser" parameterType="User">
    update mybatis.user set username=#{username},password=#{password} where id = #{id}
</update>

    <delete id="deleteUser" parameterType="int">
    delete from mybatis.user where id = #{id}
</delete>
</mapper>
```
#### SpringBoot配置文件添加映射关系            
![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580825846558.png)
#### 编写controller    
```java
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
```
## 整合MyBatis参照此流程即可，报错根据提示改正。
