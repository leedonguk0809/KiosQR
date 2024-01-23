package org.zerock.wcup.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private boolean delFlag;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
