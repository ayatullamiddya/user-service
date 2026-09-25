package com.prc.user_profile.exception;

import org.springframework.beans.factory.annotation.Value;

public class UserNotFoundException extends RuntimeException{

    @Value("${spring.application.name}")
    private static String serviceName;


   public UserNotFoundException(String msg){
       super(msg);
   }
}
