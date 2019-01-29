package com.force022.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.force022.demo.dto.ErrorMessageDto;
import com.force022.demo.dto.SessionErrorDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.force022.demo.constants.SecurityConfigConstans.XHR_FIELDS_IS_AJAX_KEY;
import static com.force022.demo.constants.SecurityConfigConstans.XHR_FIELDS_IS_AJAX_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


/**
 * 認証失敗時の処理
 */
public class RestAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint
{

    private String authenticateHeaderName;
    private String tokenPrefix;
    private ObjectMapper objectMapper = new ObjectMapper();

    public RestAuthenticationEntryPoint(String tokenPrefix, String authenticateHeaderName, String loginUrl) {
        super(loginUrl);
        this.authenticateHeaderName=authenticateHeaderName;
        this.tokenPrefix=tokenPrefix;
    }

    /**
     * Ajaxで分岐
     * 違うならコンストラクタで設定したURLへforward
     */
    @Override
    public void commence(
        HttpServletRequest request,HttpServletResponse response,AuthenticationException authException)
        throws IOException, ServletException {
        if (XHR_FIELDS_IS_AJAX_VALUE.equals(request.getHeader(XHR_FIELDS_IS_AJAX_KEY))) {
            String value;
            if (request.getRequestedSessionId() != null) {
                //期限切れ
                value = tokenPrefix + " " + "error=\"invalid_token\"";
            }else {
                //セッションなし
                value = tokenPrefix + " " + "realm=\"Realm\"";
            }
            response.setHeader(this.authenticateHeaderName, value);
            response.setContentType(APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(
                    new SessionErrorDto(new ArrayList<>(){{add(new ErrorMessageDto(value));}})));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            super.commence(request, response, authException);
        }
    }
}