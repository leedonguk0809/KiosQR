package org.zerock.wcup.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.wcup.domain.Board;
import org.zerock.wcup.dto.BoardListDTO;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void test1(){

        log.info(boardRepository.getClass().getName());
    }
    //insert save
    @Test
    public void testInsert() {

        for(int i = 0; i< 100; i++){
            Board board = Board.builder()
                    .title("테스트제목"+i)
                    .content("Test Content" + i)
                    .writer("user00")
                    .build();
            Board result = boardRepository.save(board);

            log.info(result);
        }//end for
    }//end method

    //select
    @Test
    public void testRead(){
        Long bno = 123L;
        Optional<Board> result = boardRepository.findById(bno);

        result.orElseThrow();


        log.info(result.get());

    }

    @Test
    public void testUpdate(){
        Long bno = 1L;
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        board.changeDel(true);

        boardRepository.save(board);

    }



    @Test
    public void testList(){

        //페이지 번호 0부터 , 사이즈, 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        log.info(result);

        log.info(result.getTotalElements());
        log.info(result.getTotalPages());

        result.forEach(board -> log.info(board));
    }

    @Test
    public void testQuery1(){
        Long bno = 1L;
        log.info(boardRepository.getBno(bno));
    }

    @Test
    public void testQuery2(){
        Long bno = 50L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        log.info(boardRepository.list1(bno, pageable));
    }
    @Test
    public void testQuery3(){
        String title = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        log.info(boardRepository.listTitle(title, pageable));
    }

    @Test
    public void testWithReplyCount() {

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        Page<Object[]> result =
                boardRepository.listWithReplyCount(pageable);

        log.info(result);

        result.forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testWithReplyCountDTO() {

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        Page<BoardListDTO> result =
                boardRepository.listWithReplyCountDTO(pageable);

        log.info(result);

        result.forEach(dto -> log.info(dto));
    }

}
