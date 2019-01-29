package com.force022.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.force022.demo.dto.LoginResultDto;
import org.slf4j.Logger;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.force022.demo.constants.SecurityConfigConstans.XHR_FIELDS_IS_AJAX_KEY;
import static com.force022.demo.constants.SecurityConfigConstans.XHR_FIELDS_IS_AJAX_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * ログイン成功時にJsonで出力
 * Bearer等の Prefixも対応
 */
public class PrefixHeaderHttpSessionIdResolver extends HeaderHttpSessionIdResolver {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PrefixHeaderHttpSessionIdResolver.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private String headerName;
    private Integer timeout;
    private String tokenPrefix;
    private String authenticateHeaderName;

    public PrefixHeaderHttpSessionIdResolver(String headerName, Integer timeout, String tokenPrefix, String authenticateHeaderName ){
        super(headerName);
        this.headerName = headerName;
        this.timeout=timeout;
        this.authenticateHeaderName=authenticateHeaderName;
        this.tokenPrefix = tokenPrefix.trim();
    }

    /**
     * prefix追加
     */
    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        String headerValue = request.getHeader(headerName);
        return (headerValue != null)?Collections.singletonList(headerValue.replace(tokenPrefix+" ", "").trim())
                :Collections.emptyList();
    }

    /**
     * セッションIdをJsonで出力
     * ここじゃないとSession取れない？ Todo
     */
    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response,
                             String sessionId) {
        if(response.getHeader(this.authenticateHeaderName)==null) {
            response.setHeader(this.headerName, tokenPrefix + " " + sessionId);
            if (XHR_FIELDS_IS_AJAX_VALUE.equals(request.getHeader(XHR_FIELDS_IS_AJAX_KEY))) {
                sessionIdJsonOutput(request, response, sessionId);
            }
        }
    }

    private void sessionIdJsonOutput(HttpServletRequest request, HttpServletResponse response,
                                     String sessionId){
        try {
            response.setContentType(APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(new LoginResultDto(sessionId,tokenPrefix,timeout)));
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
        }
    }
}