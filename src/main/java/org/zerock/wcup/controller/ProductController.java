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
import org.zerock.wcup.dto.ProductRegisterDTO;
import org.zerock.wcup.service.ProductService;
import org.zerock.wcup.util.CustomFileUtil;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/prd")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CustomFileUtil fileUtil;


    @GetMapping("/list")
    public void list(Model model){
        log.info("list...............");

        List<String> list = List.of("AAA","BBB","CCC","DDD","EEE");

        model.addAttribute("list",list);


    }

    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @GetMapping("/register")
    public void register( ){
        log.info("register...............");

    }

    @PostMapping("/register")
    public String registerPost(ProductRegisterDTO productRegisterDTO){
        log.info("registerPost...............");


        log.info(productRegisterDTO);


        List<String> uploadedFileNames = fileUtil.saveFiles(productRegisterDTO.getFiles());

        productRegisterDTO.setUploadFileNames(uploadedFileNames);

        Long pno = productService.register(productRegisterDTO);



        return "redirect:/prd/read/" + pno;
    }




    @GetMapping("/read/{pno}")
    public String read( @PathVariable("pno") Long pno ){

        log.info("read...............");
        log.info("pno: " + pno);

        return "/prd/read";
    }


}
