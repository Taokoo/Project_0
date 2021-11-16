package com.taokoo.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.taokoo.common.filter.AuthencationTokenFilter;
import com.taokoo.common.filter.RestAuthorizationEntryPoint;
import com.taokoo.common.filter.RestfulAccessDeniedHandler;
import com.taokoo.service.PdtUserDetailsService;

/**
 * @Desc Spring Security配置类
 * @Author Taokoo
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级安全验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PdtUserDetailsService pdtUserDetailsService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //不需要csrf
        httpSecurity.csrf()
                .disable()
                //基于token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许直接访问
                .antMatchers(HttpMethod.POST,"/**/login","/**/logout","/**/swagger-ui").permitAll()
                .antMatchers(HttpMethod.GET,"/**/login","/**/logout","/**/swagger-ui").permitAll()
                //除了上面配置的url，其他请求都需要鉴权
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                //默认关闭缓存
                .cacheControl();
        //登录授权过滤器
        httpSecurity.addFilterBefore(authencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //自定义授权操作
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(
                //静态资源
                "/css/**",
                "/js/**",
                "favicon.ico",
                // -- swagger3
                "/index.html",
                "/swagger-ui.html",
                "/swagger-ui/*",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/webjars/**",
                "/profile/**",
                "/doc.html"
        );
    }


        /**
         * 用于解决跨域问题
         *
         * @return
         */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",
                new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            UserDetails userDetails = pdtUserDetailsService.loadUserByUsername(username);
            if(null != userDetails){
                return userDetails;
            }
            return null;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthencationTokenFilter authencationTokenFilter(){
        return new AuthencationTokenFilter();
    }
}
