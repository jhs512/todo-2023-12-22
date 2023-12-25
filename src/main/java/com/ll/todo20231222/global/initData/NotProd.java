package com.ll.todo20231222.global.initData;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.member.member.service.MemberService;
import com.ll.todo20231222.domain.post.post.service.PostService;
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
    private final PostService postService;

    @Bean
    @Order(4)
    ApplicationRunner initNotProd() {
        return args -> {
            if (memberService.findByUsername("user3").isPresent()) return;

            Member memberUser3 = memberService.join("user3", "1234").getData();
            Member memberUser4 = memberService.join("user4", "1234").getData();

            postService.write(memberUser3, "제목1", "내용1", true);
            postService.write(memberUser3, "제목2", "내용2", true);
            postService.write(memberUser3, "제목3", "내용3", true);
            postService.write(memberUser3, "제목4", "내용4", true);
            postService.write(memberUser4, "제목5", "내용5", true);
            postService.write(memberUser4, "제목6", "내용6", true);
        };
    }
}
