package com.prc.user_profile.service;

import com.prc.user_profile.domain.UserProfileEntity;
import com.prc.user_profile.exception.UserAlreadyExistException;
import com.prc.user_profile.mapper.UserMapper;
import com.prc.user_profile.model.UserDTO;
import com.prc.user_profile.model.UserProfileDTO;
import com.prc.user_profile.model.UserSignupDTO;
import com.prc.user_profile.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements IUserProfileService {

    @Autowired
    private final UserProfileRepository userProfileRepository;
    @Autowired
    private final UserMapper studentMapper;
    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;
    @Autowired
    private final UserServiceDetailsImpl userServiceDetailsImpl;
    @Override
    public Flux<UserProfileDTO> getAllUserProfiles() {
        Flux<UserProfileEntity> all = userProfileRepository.findAll();
        return studentMapper.studentEntityToStudentDTO(all);


    }

    @Override
    public Mono<UserProfileDTO> getUserProfileById(String username) {
        Mono<UserProfileEntity> studentEntity = userProfileRepository.findById(username);
        return studentMapper.studentEntityToStudentDTO(studentEntity);
    }

    @Override
    @Transactional
    public Mono<UserProfileDTO> addUserProfile(UserSignupDTO student) {
        UserProfileEntity studentEntity1 = studentMapper.UserSignupDTOTostudentEntity(student);
      return  userProfileRepository.existsById(student.getEmail())
                       .flatMap(exist->
                       {
                           if (exist)
                              return Mono.error(new UserAlreadyExistException("User is exist."));

                           else
                              return r2dbcEntityTemplate.insert(studentEntity1)
                                       .flatMap(entity-> studentMapper.studentEntityToStudentDTO(Mono.just(entity)));

                       })

                        .flatMap(studentdto->{
                            if(! student.getPassword().equals(student.getConfirmedPassword()))
                                return Mono.error(new RuntimeException("Password isn't mached."));
                            return userServiceDetailsImpl
                                    .addUser(UserDTO.builder().userName(student.getEmail()).password(student.getPassword()).role("student").build())
                                   .thenReturn(studentdto);
                        });

    }
}
