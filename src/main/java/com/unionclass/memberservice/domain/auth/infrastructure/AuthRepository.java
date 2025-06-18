package com.unionclass.memberservice.domain.auth.infrastructure;

import com.unionclass.memberservice.domain.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {

    Optional<Auth> findByMemberUuid(String memberUuid);
    Optional<Auth> findByLoginId(String loginId);
}
