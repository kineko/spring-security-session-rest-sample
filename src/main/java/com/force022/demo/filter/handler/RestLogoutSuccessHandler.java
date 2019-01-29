package com.force022.demo.filter.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.force022.demo.dto.LoginResultDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * ログアウト成功時
 */
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

    private String authorizationHeaderName;
    private String tokenPrefix;
    private ObjectMapper objectMapper = new ObjectMapper();

    public RestLogoutSuccessHandler(String tokenPrefix, String authorizationHeaderName) {
        this.authorizationHeaderName=authorizationHeaderName;
        this.tokenPrefix=tokenPrefix;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String value=tokenPrefix+" "+"realm=\"logout\"";
        response.setHeader(this.authorizationHeaderName, value);
        response.setContentType(APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(new LoginResultDto(value,tokenPrefix,0)));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
