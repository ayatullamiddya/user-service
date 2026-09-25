package com.prc.user_profile.exception;

import org.springframework.beans.factory.annotation.Value;

public class UserAlreadyExistException extends RuntimeException{

    @Value("${spring.application.name}")
    private static String serviceName;


   public UserAlreadyExistException(String msg){
       super(msg);
   }
}
