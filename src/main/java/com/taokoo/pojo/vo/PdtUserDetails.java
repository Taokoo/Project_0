package com.taokoo.pojo.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

public class PdtUserDetails extends User {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
    private String token;

    public PdtUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
