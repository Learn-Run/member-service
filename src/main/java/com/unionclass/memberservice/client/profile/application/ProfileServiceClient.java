package com.unionclass.memberservice.client.profile.application;

import com.unionclass.memberservice.client.profile.dto.in.RegisterNicknameReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service")
public interface ProfileServiceClient {

    @PostMapping("/api/v1/profile/register-nickname")
    void registerNickname(@RequestBody RegisterNicknameReqDto registerNicknameReqDto);
}