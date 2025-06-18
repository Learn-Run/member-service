package com.unionclass.memberservice.common.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class OAuthUserDetails implements UserDetails {

    private final String memberUuid;

    @Builder
    public OAuthUserDetails(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.memberUuid;
    }
}
