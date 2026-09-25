package com.prc.user_profile.exception;

import org.springframework.beans.factory.annotation.Value;

public class UsernameNotFoundException extends RuntimeException{
    @Value("${spring.application.name}")
    private static String serviceName;


    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
