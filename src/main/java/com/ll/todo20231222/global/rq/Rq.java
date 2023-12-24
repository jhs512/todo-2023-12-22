package com.ll.todo20231222.global.rq;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.global.app.AppConfig;
import com.ll.todo20231222.global.exceptions.GlobalException;
import com.ll.todo20231222.global.security.SecurityUser;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final EntityManager entityManager;
    private Member member;

    // 쿠키 관련
    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setDomain(AppConfig.getSiteCookieDomain());
        resp.addCookie(cookie);
    }

    public void setCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        resp.addCookie(cookie);
    }

    private String getSiteCookieDomain() {
        String cookieDomain = AppConfig.getSiteCookieDomain();

        if (!cookieDomain.equals("localhost")) {
            return cookieDomain = "." + cookieDomain;
        }

        return null;
    }

    public void setCrossDomainCookie(String name, String value) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .path("/")
                .domain(getSiteCookieDomain())
                .secure(true)
                .httpOnly(true)
                .build();

        resp.addHeader("Set-Cookie", cookie.toString());
    }

    public void removeCrossDomainCookie(String name) {
        removeCookie(name);

        ResponseCookie cookie = ResponseCookie.from(name, null)
                .path("/")
                .maxAge(0)
                .domain(getSiteCookieDomain())
                .secure(true)
                .httpOnly(true)
                .build();

        resp.addHeader("Set-Cookie", cookie.toString());
    }

    public Cookie getCookie(String name) {
        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }

    public String getCookieValue(String name, String defaultValue) {
        Cookie cookie = getCookie(name);

        if (cookie == null) {
            return defaultValue;
        }

        return cookie.getValue();
    }

    private long getCookieAsLong(String name, int defaultValue) {
        String value = getCookieValue(name, null);

        if (value == null) {
            return defaultValue;
        }

        return Long.parseLong(value);
    }

    public void removeCookie(String name) {
        Cookie cookie = getCookie(name);

        if (cookie == null) {
            return;
        }

        cookie.setPath("/");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }

    public Member getMember() {
        if (isLogout()) return null;

        if (member == null) {
            member = entityManager.getReference(Member.class, getUser().getId());
        }

        return member;
    }

    private boolean isLogout() {
        return !isLogin();
    }

    private boolean isLogin() {
        return getUser() != null;
    }

    private SecurityUser getUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(context -> context.getAuthentication())
                .filter(authentication -> authentication.getPrincipal() instanceof SecurityUser)
                .map(authentication -> (SecurityUser) authentication.getPrincipal())
                .orElse(null);
    }

    public void setLogin(SecurityUser securityUser) {
        SecurityContextHolder.getContext().setAuthentication(securityUser.genAuthentication());
    }

    public String getHeader(String name) {
        return req.getHeader(name);
    }

    public String historyBack(GlobalException ex) {
        req.setAttribute("failMsg", ex.getRsData().getMsg());

        return "global/js";
    }

    public void setStatusCode(int statusCode) {
        resp.setStatus(statusCode);
    }

    public String getReferer(String defaultValue) {
        String referer = req.getHeader("Referer");

        if (referer == null) {
            return defaultValue;
        }

        return referer;
    }

    public boolean isFrontUrl(String url) {
        return url.startsWith(AppConfig.getSiteFrontUrl());
    }

    public void destroySession() {
        req.getSession().invalidate();
    }

    public void setLogout() {
        removeCrossDomainCookie("accessToken");
        removeCrossDomainCookie("refreshToken");
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public void setSession(String name, String value) {
        req.getSession().setAttribute(name, value);
    }
}
