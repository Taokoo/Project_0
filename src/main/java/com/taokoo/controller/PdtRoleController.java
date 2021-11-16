package com.taokoo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taokoo.common.util.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/sys/role/")
@Api(tags = "角色接口")
public class PdtRoleController {

    @GetMapping("m1")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    @Operation(summary="测试接口1：admin权限可访问")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="账号",dataTypeClass = String.class, paramType = "query",example="user1"),
    })
    public R m1(@RequestParam String username){
        System.out.println(username);
        return R.ok();
    }
}
