package com.base.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @description:
 * @author: 小猴子
 * @date: 2020-05-21 15:58
 */
@Data
public class SysUser implements UserDetails {

    private String id;

    private String username;

    public String password;

    public String realName;

    public String address;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SysUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
