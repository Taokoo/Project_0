package com.taokoo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taokoo.pojo.po.PdtUser;
import com.taokoo.pojo.vo.PdtUserDetails;

@Service
public class PdtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PdtUserService pdtUserService;


    @Override
    public PdtUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PdtUser pdtUser = pdtUserService.findUserByUsername(s);
        if (null == pdtUser) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        String roleName = pdtUserService.findRoleByUser(pdtUser);
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        PdtUserDetails user = new PdtUserDetails(pdtUser.getUsername(),passwordEncoder.encode(pdtUser.getPassword()),authorities);
        return user;
    }

    public PdtUser getUser(String username){
        return pdtUserService.findUserByUsername(username);
    }
}
