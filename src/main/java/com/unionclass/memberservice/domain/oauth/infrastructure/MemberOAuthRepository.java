package com.unionclass.memberservice.domain.oauth.infrastructure;

import com.unionclass.memberservice.domain.oauth.entity.MemberOAuth;
import com.unionclass.memberservice.domain.oauth.enums.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberOAuthRepository extends JpaRepository<MemberOAuth, Long> {

    Optional<MemberOAuth> findByProviderAndProviderAccountId(Provider provider, String providerAccountId);
    boolean existsByProviderAndProviderAccountId(Provider provider, String providerAccountId);
}
