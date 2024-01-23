package org.zerock.wcup.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.wcup.domain.Board;
import org.zerock.wcup.dto.BoardDTO;
import org.zerock.wcup.dto.BoardListDTO;
import org.zerock.wcup.dto.PageRequestDTO;
import org.zerock.wcup.dto.PageResponseDTO;
import org.zerock.wcup.repository.BoardRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public PageResponseDTO<BoardListDTO> list(PageRequestDTO pageRequestDTO){

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("bno").descending()
                );
        Page<BoardListDTO> result = boardRepository.listWithReplyCountDTO(pageable);

        long total = result.getTotalElements();
        int current = pageRequestDTO.getPage();
        int size = pageRequestDTO.getSize();

        return new PageResponseDTO<>(total,current,size,result.toList());
    }

    public BoardDTO get(Long bno){

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    public BoardDTO register(BoardDTO boardDTO){

        Board board = modelMapper.map(boardDTO, Board.class);
        log.info(board);

        boardRepository.save(board);

        log.info("after..............");

        log.info(board);

        return modelMapper.map(board, BoardDTO.class);

    }
}
