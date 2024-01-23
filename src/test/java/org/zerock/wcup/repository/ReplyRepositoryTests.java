package org.zerock.wcup.repository;


import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.wcup.domain.Board;
import org.zerock.wcup.domain.Reply;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testInsert() {
        //111, 109, 108, 106

        Long bno = 106L;
        for (int i = 0; i < 5; i++) {
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .replyText("Test")
                    .replyer("r1")
                    .board(board)
                    .build();

            log.info(replyRepository.save(reply));
        }//end for
    }


//    @Test
//    public void testRead(){
//        Long rno = 20L;
//
//        Optional<Reply> result = replyRepository.findById(rno);
//
//        Reply reply = result.orElseThrow();
//
//        log.info(reply);
//    }

}
