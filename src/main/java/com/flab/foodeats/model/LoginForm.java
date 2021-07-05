package com.flab.foodeats.model;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * 로그인 기반 코드
 * @author yusok
 */

// 로그인시 필요한 값
@Data
public class LoginForm {
    @NotEmpty
    private String id; // 로그인 id
    @NotEmpty
    private String password; // 로그인 password
}

