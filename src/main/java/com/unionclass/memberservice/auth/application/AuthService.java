package com.unionclass.memberservice.auth.application;

import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;

public interface AuthService {

    void signUp(SignUpReqDto signUpReqDto);
}
