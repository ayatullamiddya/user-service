package com.prc.user_profile;

import com.prc.user_profile.handler.UserHandler;
import com.prc.user_profile.openApi.UserOpenApi;
import org.springdoc.webflux.core.fn.SpringdocRouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class Router {
        public static final String prefix = "v1/studentapi/";
       /* @Bean
        RouterFunction<ServerResponse> studentsRoute(StudentHandler handler){
            return RouterFunctions.route(GET("students").and(accept(MediaType.APPLICATION_JSON)),handler::getAllStudents);
        }*/


        @Bean
        RouterFunction<ServerResponse> studentsRoute1(UserHandler handler){
           return SpringdocRouteBuilder.route()
                    .GET(prefix+"students",handler::getAllStudents, UserOpenApi.studentGetAllStudentsAPI())
                   .build();
        }

        @Bean
        RouterFunction<ServerResponse> usersApi(UserHandler handler){
            return SpringdocRouteBuilder.route()
                    .POST(prefix+"signup",handler::signupUser,UserOpenApi.signupUserAPI())
                     .build();
        }
}
