package com.korit.servlet_study.ch03;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    //User엔티티는 데이터베이스를 연결해주는 인터페이스
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;

}
