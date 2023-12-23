package com.ll.todo20231222.global.initData;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;

    @Bean
    @Order(4)
    ApplicationRunner initNotProd() {
        return args -> {
            if (memberService.findByUsername("user4").isPresent()) return;

            Member memberUser3 = memberService.join("user3", "1234").getData();
            Member memberUser4 = memberService.join("user4", "1234").getData();
        };
    }
}
