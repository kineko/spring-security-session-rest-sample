package com.force022.demo;

import com.force022.demo.filter.PrefixHeaderHttpSessionIdResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HttpSessionIdResolver;

import static com.force022.demo.constants.SecurityConfigConstans.*;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = REDIS_SESSION_TIMEOUT)
@Configuration
public class SessionConfig {

    //何故かboot.starterからとれないので無理やりymlから取得
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        //return HeaderHttpSessionIdResolver.xAuthToken();
        return new PrefixHeaderHttpSessionIdResolver(AUTHORIZATION_HEADER,REDIS_SESSION_TIMEOUT,TOKEN_PREFIX,AUTHENTICATE_HEADER);
    }

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost,redisPort));
    }

}
