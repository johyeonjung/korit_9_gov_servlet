package com.korit.servlet_study.ch07;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Board {
    private String title;
    private String content;
    private String writer;

}
