package cn.edu.upc.yb.common.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by lylll on 2017/6/2.
 */
public class JwtUser implements UserDetails {

    private final int id;
    private final int userid;
    private final String username;
    private final String usernick;
    private final char usersex;

    private final Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private final Date lastPasswordResetDate;


    public JwtUser(int id, int userid, String username, String usernick, char usersex, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userid = userid;
        this.usernick = usernick;
        this.usersex = usersex;
        this.username = username;
        this.authorities = authorities;
        this.lastPasswordResetDate = null;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsernick() {
        return usernick;
    }

    public char getUsersex() {
        return usersex;
    }

    public int getId() {
        return id;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
