package com.ll.todo20231222.domain.home.home.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
@Tag(name = "ApiV1HomeController", description = "HOME 컨트롤러")
public class ApiV1HomeController {
    @GetMapping("/names")
    public List<String> getNames() {
        return List.of("hello!!", "world!!");
    }
}
