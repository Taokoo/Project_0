package com.taokoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taokoo.common.util.R;
import com.taokoo.dao.PdtRoleMapper;
import com.taokoo.dao.PdtUserMapper;
import com.taokoo.dao.PdtUserRoleMapper;
import com.taokoo.pojo.po.PdtRole;
import com.taokoo.pojo.po.PdtUser;
import com.taokoo.pojo.po.PdtUserRole;

@Service
public class PdtUserService {

    @Autowired
    private PdtUserMapper pdtUserMapper;
    @Autowired
    private PdtUserRoleMapper pdtUserRoleMapper;
    @Autowired
    private PdtRoleMapper pdtRoleMapper;
//    @Autowired
//    private PdtResourceMapper pdtResourceMapper;
//    @Autowired
//    private PdtRoleResourceMapper pdtRoleResourceMapper;

    public PdtUser findUserByUsername(String username){
        QueryWrapper<PdtUser> qw = new QueryWrapper<>();
        qw.eq("username",username).eq("status",1);
        List<PdtUser> lst = pdtUserMapper.selectList(qw);
        if(lst.size() > 0){
            return lst.get(0);
        }
        return null;
    }

    public PdtUser login(String username,String password){
        QueryWrapper<PdtUser> qw = new QueryWrapper<>();
        qw.eq("username",username).eq("password",password);
        List<PdtUser> lst = pdtUserMapper.selectList(qw);
        if(lst.size() > 0){
            return lst.get(0);
        }
        return null;
    }

    public R findList(){
        List<PdtUser> lst = pdtUserMapper.selectList(null);
        return R.ok().put("lst",lst);
    }

    /**
     * @Desc 通过用户获取角色名称
     * @Author Taokoo
     */
    public String findRoleByUser(PdtUser user){
        QueryWrapper<PdtUserRole> qwUserRole = new QueryWrapper<>();
        qwUserRole.eq("user_id",user.getId());
        List<PdtUserRole> userRoleLst = pdtUserRoleMapper.selectList(qwUserRole);
        if(userRoleLst.size() > 0){
            PdtUserRole userRole = userRoleLst.get(0);
            QueryWrapper<PdtRole> qwRole = new QueryWrapper<>();
            qwRole.eq("id",userRole.getRoleId());
            PdtRole role = pdtRoleMapper.selectOne(qwRole);
            return role.getRolename();
        }
        return null;
    }
}
