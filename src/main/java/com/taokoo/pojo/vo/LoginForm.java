package com.taokoo.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="用户登录参数",title="用户登录参数",description="用户登录参数")
public class LoginForm {

    @Schema(description="账号",example="user1")
    private String username;

    @Schema(description="密码",example="1234")
    private String password;

    @Schema(description="年龄",example="18")
    private Integer age;
}
