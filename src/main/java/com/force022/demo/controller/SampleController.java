package com.force022.demo.controller;

import com.force022.demo.dto.HelloResultDto;
import com.force022.demo.dto.PasswordCryptResultDto;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SampleController.class);
    private PasswordEncoder passwordEncoder;

    public SampleController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/admin/test")
    public HelloResultDto adminTest(Authentication authentication) {
        return new HelloResultDto("Hello "+authentication.getName()+". You are admin!");
    }

    @GetMapping(value = "/user/test")
    public HelloResultDto userTest(Authentication authentication) {
        return new HelloResultDto("Hello "+authentication.getName()+". You are user!");
    }

    @GetMapping(value = "/all/crypt",produces= MediaType.APPLICATION_JSON_VALUE)
    public PasswordCryptResultDto adminRegist(@RequestBody String password) {
        String digest = passwordEncoder.encode(password);
        log.debug("digest = "+ digest);
        return new PasswordCryptResultDto(password,digest);
    }
}
