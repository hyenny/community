package org.example.community.common.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
    responseCode = "400",
    description = "잘못된 요청",
    content = @Content(
        mediaType = "application/json",
        examples = {
            @ExampleObject(
                name = "ValidationError",
                summary = "입력값 검증 오류",
                value = """
                    {
                      "status": 400,
                      "message": "유효하지 않은 입력값입니다.",
                      "errors": [
                        {
                          "field": "content",
                          "reason": "내용은 필수 값입니다."
                        }
                      ]
                    }
                    """
            ),
            @ExampleObject(
                name = "InvalidError",
                summary = "잘못된 입력값 오류",
                value = """
                    {
                      "status": 400,
                      "message": "이미 등록된 이메일입니다.",
                      "errors": []
                    }
                    """
            )
        }
    )
)
@ApiResponse(
    responseCode = "401",
    description = "인증 실패",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = ErrorResponse.class,
            example = """
            {
              "status": 401,
              "message": "잘못된 인증 정보입니다.",
              "errors": []
            }
            """
        )
    )
)
@ApiResponse(
    responseCode = "403",
    description = "권한 오류",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = ErrorResponse.class,
            example = """
            {
              "status": 403,
              "message": "접근 권한이 없습니다.",
              "errors": []
            }
            """
        )
    )
)
@ApiResponse(
    responseCode = "404",
    description = "존재하지 않는 리소스 접근",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = ErrorResponse.class,
            example = """
            {
              "status": 404,
              "message": "해당 게시글이 존재하지 않습니다.",
              "errors": []
            }
            """
        )
    )
)
@ApiResponse(
    responseCode = "500",
    description = "서버 내부 오류",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(
            implementation = ErrorResponse.class,
            example = """
            {
              "status": 500,
              "message": "서버 내부 오류가 발생했습니다.",
              "errors": []
            }
            """
        )
    )
)
public @interface ApiErrorResponses {

}
