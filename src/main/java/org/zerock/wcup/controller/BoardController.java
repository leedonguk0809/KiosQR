package org.zerock.wcup.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.wcup.dto.BoardDTO;
import org.zerock.wcup.dto.BoardListDTO;
import org.zerock.wcup.dto.PageRequestDTO;
import org.zerock.wcup.dto.PageResponseDTO;
import org.zerock.wcup.service.BoardService;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list...............");
        log.info(pageRequestDTO);
        model.addAttribute("result",boardService.list(pageRequestDTO));
    }

    @GetMapping("/{bno}")
    public String get(@PathVariable("bno") Long bno, Model model){

        BoardDTO boardDTO = boardService.get(bno);

        model.addAttribute("board", boardDTO);

        return "board/get";
    }

    @GetMapping("/register")
    public void registerGet(){

    }


    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, RedirectAttributes redirectAttributes){

        log.info("boardDTO: " + boardDTO);

        BoardDTO result = boardService.register(boardDTO);

        log.info("after register-------------------");
        log.info(result);

        redirectAttributes.addFlashAttribute("msg", result);

        return "redirect:/board/list";
    }
}
