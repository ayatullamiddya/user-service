package com.prc.user_profile.handler;

import com.prc.user_profile.exception.UsernameNotFoundException;
import com.prc.user_profile.model.GenericResponse;
import com.prc.user_profile.model.UserProfileDTO;
import com.prc.user_profile.model.UserDTO;
import com.prc.user_profile.model.UserSignupDTO;
import com.prc.user_profile.service.IUserProfileService;
import com.prc.user_profile.service.UserServiceDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Component
public class UserHandler {
    private final IUserProfileService studentService;
    private final UserServiceDetailsImpl userServiceDetailsImpl;


    public Mono<ServerResponse> getAllStudents(ServerRequest serverRequest){
        Flux<UserProfileDTO> allStudents = studentService.getAllUserProfiles();
        return ServerResponse.ok().body(allStudents, UserProfileDTO.class);
    }

    public Mono<ServerResponse> signupUser(ServerRequest serverRequest){

            return serverRequest.bodyToMono(UserSignupDTO.class)
                    .flatMap(student->
                          userServiceDetailsImpl.findByUsername(student.getEmail())
                                .flatMap(existing ->
                                     ServerResponse.status(HttpStatus.CONFLICT)
                                            .bodyValue("User is alredy exist")
                                ).onErrorResume(UsernameNotFoundException.class,ex->
                                                 studentService.addUserProfile(student)
                                                          .flatMap(userDto->
                                                                  ServerResponse.status(HttpStatus.CREATED).bodyValue(userDto))
                                                         /* .flatMap(studentdto->{
                                              if(! student.getPassword().equals(student.getConfirmedPassword()))
                                                  return ServerResponse.badRequest().body(new GenericResponse("Password isn't matched."),GenericResponse.class);
                                              return userServiceDetailsImpl
                                                      .addUser(UserDTO.builder().userName(student.getEmail()).password(student.getPassword()).role("student").build())
                                                      .then(ServerResponse.status(HttpStatus.CREATED).bodyValue(studentdto));
                                                  }
                                          ))*/
                                                            .onErrorResume(error1->ServerResponse.badRequest().bodyValue("Signup failed: "+error1.getMessage()))
                                 ).onErrorResume(error->
                                          ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Unexpected error: " + error.getMessage())
                                  )
                    );
    }
}

