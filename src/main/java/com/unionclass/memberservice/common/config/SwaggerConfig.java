package com.unionclass.memberservice.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "auth", description = "인증 관련 API 입니다."),
                @Tag(name = "oauth", description = "소셜 로그인 관련 API 입니다."),
                @Tag(name = "email", description = "이메일 관련 API 입니다."),
                @Tag(name = "grade", description = "등급 관련 API 입니다."),
                @Tag(name = "member", description = "회원정보 관련 API 입니다."),
                @Tag(name = "member_agreement", description = "회원 약관동의 항목 관련 API 입니다."),
                @Tag(name = "agreement", description = "약관동의 항목 관련 API 입니다.")
        }
)
@Configuration
public class SwaggerConfig {

        private static final String BEARER_TOKEN_PREFIX = "Bearer";

        @Bean
        public OpenAPI openAPI() {
                String securityJwtName = "JWT";
                SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityJwtName);
                Components components = new Components()
                        .addSecuritySchemes(securityJwtName, new SecurityScheme()
                                .name(securityJwtName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(BEARER_TOKEN_PREFIX)
                                .bearerFormat(securityJwtName));

                return new OpenAPI()
                        .addSecurityItem(securityRequirement)
                        .components(components)
                        .addServersItem(new Server().url("/member-service"))
                        .info(apiInfo());
        }

        private Info apiInfo() {
                return new Info()
                        .title("MEMBER-SERVICE API DOCS")
                        .description("member-service API 테스트를 위한 Swagger UI")
                        .version("1.0.0");
        }
}