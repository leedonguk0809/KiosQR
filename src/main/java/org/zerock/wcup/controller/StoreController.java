package org.zerock.wcup.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.wcup.dto.PageRequestDTO;
import org.zerock.wcup.dto.StoreDTO;
import org.zerock.wcup.dto.UpdateTokenDTO;
import org.zerock.wcup.repository.StoreRepository;
import org.zerock.wcup.service.StoreService;
import org.zerock.wcup.util.QRCodeMaker;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/store")
@Log4j2
@RequiredArgsConstructor
public class StoreController {


    private final QRCodeMaker qrCodeMaker;

    private final StoreService storeService;

    private final PasswordEncoder passwordEncoder;

    @Value("${org.zerock.host}")
    private String hostStr;



    @GetMapping("/register")
    public void register() {

        log.info("StoreController register............");
    }

    @PostMapping("/register")
    public void registerPost(StoreDTO storeDTO) {

        log.info("storeDTO ");
        log.info(storeDTO);

        String fileName = UUID.randomUUID().toString() + ".png";

        storeDTO.setQrImage(fileName);

        String encodedPw = passwordEncoder.encode(storeDTO.getPw());

        storeDTO.setPw(encodedPw);

        Long sno = storeService.register(storeDTO);

        //make qrcode
        String qrCodeFileName = qrCodeMaker.make(hostStr + "/" + sno);
    }


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){



        log.info("list...............");
        log.info(pageRequestDTO);
        model.addAttribute("result", storeService.list(pageRequestDTO) );
    }


    @GetMapping("/dashboard")
    public void dashboard () {

        log.info("dashboard............");

    }

    @ResponseBody
    @PostMapping("/updateToken")
    public java.util.Map<String, String> setToken (@RequestBody UpdateTokenDTO updateTokenDTO){

        log.info(updateTokenDTO);

        storeService.updateToken(updateTokenDTO);

        return Map.of("result", "success");
    }






}
