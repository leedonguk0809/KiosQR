package org.zerock.wcup.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@Table(name = "tbl_reply",
        indexes = {
            @Index(name = "idx_reply_board",
                    columnList = "board_bno")
        })

public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_bno")
    private Board board;

}
