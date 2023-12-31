package com.ll.todo20231222;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Todo20231222Application {

    public static void main(String[] args) {
        SpringApplication.run(Todo20231222Application.class, args);
    }

}
