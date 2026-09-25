package com.prc.user_profile.mapper;

import com.prc.user_profile.domain.UserProfileEntity;
import com.prc.user_profile.model.UserProfileDTO;
import com.prc.user_profile.model.UserSignupDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserMapper {
    public Flux<UserProfileDTO> studentEntityToStudentDTO(Flux<UserProfileEntity> studentEntityList){
      return studentEntityList.map(studentEntity -> {
                     return   UserProfileDTO.builder().email(studentEntity.getEmail())
                              .firstname(studentEntity.getFirstname())
                             .lastname(studentEntity.getLastname())
                             .department(studentEntity.getDepartment())
                             .gender(studentEntity.getGender())
                             .coursename(studentEntity.getCoursename())
                              .build();
        });
    }

    public Mono<UserProfileDTO> studentEntityToStudentDTO(Mono<UserProfileEntity> studentEn){
        return studentEn.map(studentEntity -> {
            return   UserProfileDTO.builder().email(studentEntity.getEmail())
                    .firstname(studentEntity.getFirstname())
                    .lastname(studentEntity.getLastname())
                    .department(studentEntity.getDepartment())
                    .gender(studentEntity.getGender())
                    .coursename(studentEntity.getCoursename())
                    .build();
        });
    }
    public UserProfileEntity userDTOToUserEntity(UserProfileDTO studentDto){
        return
                UserProfileEntity.builder().email(studentDto.getEmail())
                    .firstname(studentDto.getFirstname())
                    .lastname(studentDto.getLastname())
                    .department(studentDto.getDepartment())
                    .gender(studentDto.getGender())
                    .coursename(studentDto.getCoursename())
                    .build();

    }
    public UserProfileEntity UserSignupDTOTostudentEntity(UserSignupDTO studentDto){
        return
                UserProfileEntity.builder().email(studentDto.getEmail())
                        .firstname(studentDto.getFirstname())
                        .lastname(studentDto.getLastname())
                        .department(studentDto.getDepartment())
                        .gender(studentDto.getGender())
                        .coursename(studentDto.getCoursename())
                        .build();

    }
}
