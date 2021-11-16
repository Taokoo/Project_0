package com.taokoo.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.taokoo.common.redis.RedisUtil;
import com.taokoo.pojo.po.PdtUser;
import com.taokoo.pojo.vo.PdtUserDetails;
import com.taokoo.service.PdtUserDetailsService;

/**
 * @Desc 自定义登录授权过滤器
 * @Author Taokoo
 */
public class AuthencationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PdtUserDetailsService pdtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(null != token){
            //校验token是否过期
            PdtUser pdtUser = (PdtUser)redisUtil.get(token);
            System.out.println(pdtUser);
            if(null != pdtUser && null == SecurityContextHolder.getContext().getAuthentication()){
                //token未过期，登录Spring Security授权设置用户对象
                PdtUserDetails pdtUserDetails = pdtUserDetailsService.loadUserByUsername(pdtUser.getUsername());
                //注册security时将token存入到Context中，方便通过Context+redis取出当前pdtUser的信息
                pdtUserDetails.setToken(token);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(pdtUserDetails,null,pdtUserDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
