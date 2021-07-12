package com.flab.foodeats.model;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * 로그인 기반 코드
 * @author yusok
 */

// 현재는 연습용으로 간단히 3가지 정보를 저장하지만 추후 수정 예정
// 회원가입에 필요한 값
@Data
public class Member {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
