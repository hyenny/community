package org.example.community.domain.auth.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "로그인 응답")
public record LoginResponse(
    @Schema(
        description = "인증 토큰",
        example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjFAdGVzdC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTiIsImlhdCI6MTczODQyMTE1MiwiZXhwIjoxNzM4NTA3NTUyfQ.sII0WlLYfCNZ17iAkmGuWHuXbseLotcKGrlD8xHlSC9Zz3VqkDu_hdLjUe6afM4-LIUX2JoyTPyRliyhuEw_QQ",
        requiredMode = RequiredMode.REQUIRED
    )
    String token
) {

}
