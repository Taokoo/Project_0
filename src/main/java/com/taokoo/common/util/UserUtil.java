package com.taokoo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.taokoo.common.redis.RedisUtil;
import com.taokoo.pojo.po.PdtUser;
import com.taokoo.pojo.vo.PdtUserDetails;

@Component
public class UserUtil {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @Desc 获取当前登录用户信息
     * @Author Taokoo
     */
    public PdtUser getCurrentUser(){
        PdtUserDetails pdtUserDetails = (PdtUserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        PdtUser pdtUser = (PdtUser)redisUtil.get(pdtUserDetails.getToken());
        return pdtUser;
    }
}
