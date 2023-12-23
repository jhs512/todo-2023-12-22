package com.ll.todo20231222.domain.home.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public List<String> getNames() {
        return List.of("ABC", "BBC", "DEF");
    }
}
