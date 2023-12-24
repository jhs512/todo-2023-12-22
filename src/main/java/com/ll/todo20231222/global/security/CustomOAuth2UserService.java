package com.ll.todo20231222.global.security;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberService memberService;

    // 카카오톡 로그인이 성공할 때 마다 이 함수가 실행된다.
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("A1");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("A2");
        String oauthId = oAuth2User.getName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        log.info("A3");
        Map attributesProperties = (Map) attributes.get("properties");
        String nickname = (String) attributesProperties.get("nickname");
        String profileImgUrl = (String) attributesProperties.get("profile_image");
        log.info("A4");
        String providerTypeCode = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        log.info("A5");
        String username = providerTypeCode + "__%s".formatted(oauthId);
        log.info("A6");
        Member member = memberService.whenSocialLogin(providerTypeCode, username, nickname, profileImgUrl).getData();
        log.info("A7");
        SecurityUser securityUser = new SecurityUser(member.getId(), member.getUsername(), member.getPassword(), member.getAuthorities());
        log.info("A8");

        return securityUser;
    }
}
