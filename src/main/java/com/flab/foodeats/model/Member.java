package com.flab.foodeats.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    @NotEmpty
    private String memberId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
