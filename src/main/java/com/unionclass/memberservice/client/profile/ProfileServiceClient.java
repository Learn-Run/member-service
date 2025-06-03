package com.unionclass.memberservice.client.profile;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "profile-service")
public interface ProfileServiceClient {

    public void registerNickname();
}
