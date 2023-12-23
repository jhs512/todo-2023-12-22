package com.ll.todo20231222.global.initData;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class All {
    private final MemberService memberService;

    @Bean
    @Order(2)
    ApplicationRunner initAll() {
        return args -> {
            if (memberService.findByUsername("admin").isPresent()) return;

            Member memberAdmin = memberService.join("admin", "1234").getData();
            Member memberUser1 = memberService.join("user1", "1234").getData();
            Member memberUser2 = memberService.join("user2", "1234").getData();
        };
    }
}
