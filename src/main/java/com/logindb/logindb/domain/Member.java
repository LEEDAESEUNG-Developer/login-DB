package com.logindb.logindb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Member {
    private Long memberNumber;
    private String id;
    private String pwd;
}
