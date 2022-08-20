package com.hurrypizza.digda.config.security.common;

import com.hurrypizza.digda.config.security.UserInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

@Getter
public class UserPrincipal {

    private final UserInfo userInfo;
    private final Map<String, Object> attributes;
    private final Collection<GrantedAuthority> authorities = new LinkedList<>();

    @Builder
    public UserPrincipal(UserInfo userInfo, Map<String, Object> attributes) {
        this.userInfo = userInfo;
        this.attributes = attributes;
        authorities.add(new SimpleGrantedAuthority(userInfo.getRole().toString()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getName() {
        return userInfo.getEmail();
    }

}
