package com.force022.demo;


import com.force022.demo.filter.RestAuthenticationEntryPoint;
import com.force022.demo.filter.handler.RestAccessDeniedHandler;
import com.force022.demo.filter.handler.RestAuthenticationFailureHandler;
import com.force022.demo.filter.handler.RestLogoutSuccessHandler;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.force022.demo.constants.SecurityConfigConstans.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    //private static final Logger log = org.slf4j.LoggerFactory.getLogger(SecurityConfig.class);
    private DataSource dataSource;
    public SecurityConfig(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    //Webベースのセキュリティを設定
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //リクエスト承認ロジック
                .authorizeRequests()
                    //上から順にマッチしたものを優先判定する模様
                    //マッチしたアドレスはロールチェックで許可
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasRole("USER")
                    .antMatchers("/all/**").permitAll()
                    //全ての任意のリクエストは認証済みで許可
                    .anyRequest().authenticated()
                //デフォログインロジック 全員許可
                .and().formLogin().permitAll()
                    //成功
                    .successHandler((req,res,auth)->{/*リダイレクト無効化 responce処理はSpringSessionへ*/})
                    //失敗
                    .failureHandler(new RestAuthenticationFailureHandler(TOKEN_PREFIX, AUTHENTICATE_HEADER))
                //認証失敗 ロジック
                .and().exceptionHandling()
                    //トークン認証失敗
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint(TOKEN_PREFIX, AUTHENTICATE_HEADER,"/login" ))
                    //antMatchersでロールなし
                    .accessDeniedHandler(new RestAccessDeniedHandler(TOKEN_PREFIX, AUTHENTICATE_HEADER))
                //デフォログアウトロジック 全員許可
                .and().logout().permitAll()
                    //ログアウト
                    .logoutSuccessHandler((new RestLogoutSuccessHandler(TOKEN_PREFIX,AUTHORIZATION_HEADER)))
                //Tokenなので不要
                //.and().cors()
                //csrfはdiable
                .and().csrf().disable();
    }

    //認証サービス登録
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    //Autowiredでも使う為 Bean化
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //user情報ID/PASS/ROLE提供サービス
    @Bean
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
        jdbcDaoImpl.setEnableGroups(true);
        jdbcDaoImpl.setDataSource(dataSource);
        return jdbcDaoImpl;
    }
}
