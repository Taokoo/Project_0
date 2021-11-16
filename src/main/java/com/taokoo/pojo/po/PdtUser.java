package com.taokoo.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdtUser {

    private String id;

    private String username;

    private String password;

    private String realname;

    private String mail;

    private String phone;

    private Integer status;

}
