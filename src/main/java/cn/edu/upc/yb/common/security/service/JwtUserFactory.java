package cn.edu.upc.yb.common.security.service;

import cn.edu.upc.yb.common.security.model.Authority;
import cn.edu.upc.yb.common.security.model.YBUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lylll on 2017/6/2.
 */
public final class JwtUserFactory {

    public static JwtUser create(YBUser ybUser) {
        return new JwtUser(
                ybUser.getId(),
                ybUser.getUserid(),
                ybUser.getUsername(),
                ybUser.getUsernick(),
                ybUser.getUsersex(),
                mapToGrantedAuthorities(ybUser.getAuthorities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
