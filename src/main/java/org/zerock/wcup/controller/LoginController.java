package org.zerock.wcup.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.wcup.service.StoreService;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final StoreService storeService;

    @PreAuthorize("permitAll()")
    @GetMapping("/customLogin")
    public void login(){
        log.info("custom login page");
    }

}
