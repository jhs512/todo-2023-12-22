package com.ll.todo20231222.global.initData;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"dev", "test"})
@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;

    @Bean
    ApplicationRunner initNotProd() {
        return args -> {
            if (memberService.count() > 0) return;

            Member member1 = memberService.join("admin", "1234").getData();
            Member member2 = memberService.join("user1", "1234").getData();
            Member member3 = memberService.join("user2", "1234").getData();
        };
    }
}
