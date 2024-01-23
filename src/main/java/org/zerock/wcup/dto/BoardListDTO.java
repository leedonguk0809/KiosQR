package org.zerock.wcup.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListDTO {

    private Long bno;

    private String title;

    private String writer;

    private java.time.LocalDateTime regDate;

    private long replyCount;

    public BoardListDTO(Long bno, String title, String writer, LocalDateTime regDate, long replyCount) {
        this.bno = bno;
        this.title = title;
        this.writer = writer;
        this.regDate = regDate;
        this.replyCount = replyCount;
    }
}
