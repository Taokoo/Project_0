package com.taokoo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taokoo.common.redis.RedisUtil;
import com.taokoo.common.util.R;
import com.taokoo.pojo.po.PdtUser;
import com.taokoo.pojo.vo.LoginForm;
import com.taokoo.pojo.vo.PdtUserDetails;
import com.taokoo.service.PdtUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

@RestController
@RequestMapping("/sys/user/")
@Api(tags = "用户接口")
public class PdtUserController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PdtUserDetailsService pdtUserDetailsService;

    @PostMapping("login")
    @ResponseBody
    @Operation(summary ="登录")
    public R login(@RequestBody LoginForm e){
        String username = e.getUsername();
        String password = e.getPassword();
        if(null == username || null == password || "".equals(username) || "".equals(password)){
            return R.error("缺少登录参数，请检查");
        }
        PdtUserDetails pdtUserDetails = pdtUserDetailsService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password,pdtUserDetails.getPassword())){
            return R.error("用户名或密码不正确");
        }
        String token = UUID.randomUUID().toString().replace("-","");
        PdtUser pdtUser = pdtUserDetailsService.getUser(pdtUserDetails.getUsername());
        redisUtil.set(token, pdtUser,600);
        return R.ok("200","登录成功").put("token",token);
    }

    @GetMapping("m1")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    @Operation(summary="测试接口1：admin权限可访问")
    @Parameters({
            @Parameter(name="username",description="账号",example="user1"),
            @Parameter(name="age",description="年龄",example="18"),
    })
    public R m1(@RequestParam String username, @RequestParam Integer age){
        System.out.println(username);
        return R.ok();
    }

    @PostMapping("m2")
    @ResponseBody
    @PreAuthorize("hasRole('user1')")
    @Operation(summary="测试接口2：user1权限可访问")
    public R m2(){
        return R.ok();
    }
}
