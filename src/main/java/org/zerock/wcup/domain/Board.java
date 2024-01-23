package org.zerock.wcup.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 100, nullable = false)
    private String title;

    private String content;

    private String writer;

    private boolean delFlag;

    public void changeTitle(String title){
        this.title = title;
    }
    public void changeContent(String content){
        this.content = content;
    }
    public void changeDel(boolean delFlag){
        this.delFlag = delFlag;
    }

}
