package org.zerock.wcup.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.wcup.dto.BoardDTO;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    BoardService boardService;

    @Test
    public void testGet(){
        BoardDTO boardDTO = boardService.get(50L);

        log.info(boardDTO);
    }


}
