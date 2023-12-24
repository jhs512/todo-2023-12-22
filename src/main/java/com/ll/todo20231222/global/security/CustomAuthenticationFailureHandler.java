package com.ll.todo20231222.global.security;

import com.ll.todo20231222.global.rq.Rq;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final Rq rq;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String redirectUrlAfterSocialLogin = rq.getCookieValue("redirectUrlAfterSocialLogin", "");

        if (rq.isFrontUrl(redirectUrlAfterSocialLogin)) {
            String providerTypeCode = rq.getCookieValue("providerTypeCode", "");

            response.sendRedirect("/member/socialLogin/" + providerTypeCode + "?redirectUrl=" + redirectUrlAfterSocialLogin);
            return;
        }

        super.onAuthenticationFailure(request, response, exception);
    }
}
