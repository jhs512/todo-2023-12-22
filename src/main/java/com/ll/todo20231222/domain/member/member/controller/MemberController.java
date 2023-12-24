package com.ll.todo20231222.domain.member.member.controller;

import com.ll.todo20231222.domain.member.member.dto.MemberDto;
import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.global.rq.Rq;
import com.ll.todo20231222.global.rsData.RsData;
import com.ll.todo20231222.standard.base.Empty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.util.MimeTypeUtils.ALL_VALUE;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "MemberController", description = "회원 컨트롤러")
public class MemberController {
    private final Rq rq;

    @GetMapping("/socialLogin/{providerTypeCode}")
    @Operation(summary = "소셜 로그인")
    public String socialLogin(String redirectUrl, @PathVariable String providerTypeCode) {
        if (rq.isFrontUrl(redirectUrl)) {
            rq.setCookie("providerTypeCode", providerTypeCode, 60 * 10);
            rq.setCookie("redirectUrlAfterSocialLogin", redirectUrl, 60 * 10);
        }

        return "redirect:/oauth2/authorization/" + providerTypeCode;
    }

    @Getter
    public static class MeResponseBody {
        @NonNull
        private MemberDto item;

        public MeResponseBody(Member member) {
            this.item = new MemberDto(member);
        }
    }

    @GetMapping(value = "/me", consumes = ALL_VALUE)
    @Operation(summary = "내 정보")
    public RsData<MeResponseBody> getMe() {
        return RsData.of(
                "200",
                "내 정보 가져오기 성공",
                new MeResponseBody(rq.getMember())
        );
    }

    @PostMapping(value = "/logout", consumes = ALL_VALUE)
    @Operation(summary = "로그아웃")
    public RsData<Empty> logout() {
        rq.setLogout();

        return RsData.of("200", "로그아웃 성공");
    }
}
