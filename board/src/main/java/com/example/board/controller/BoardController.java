package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/board")
@Log4j2
@Controller
public class BoardController {

    @GetMapping("/list")
    public void getList() {
        log.info("List 요청");
    }

}
