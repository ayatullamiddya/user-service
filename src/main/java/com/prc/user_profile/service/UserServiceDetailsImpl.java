package com.prc.user_profile.service;

import com.prc.user_profile.domain.RolesEntity;
import com.prc.user_profile.domain.UserEntity;
import com.prc.user_profile.exception.UserAlreadyExistException;
import com.prc.user_profile.exception.UsernameNotFoundException;
import com.prc.user_profile.model.UserDTO;
import com.prc.user_profile.repository.RoleRepository;
import com.prc.user_profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class UserServiceDetailsImpl implements ReactiveUserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<UserEntity> byId = userRepository.findById(username);
       return byId.switchIfEmpty(Mono.error(()->{
            return new UsernameNotFoundException("User is unauthorized : "+username);
        })).map(user->
                User.withUsername(username).password(user.getPassword()).roles("student").build()
        );
    }

    public Mono<String> deleteByUsername(String username) {
        Mono<UserEntity> byId = userRepository.findById(username);
        return byId.switchIfEmpty(Mono.error(()->{
            throw new UsernameNotFoundException("User is unauthorized : "+username);
        })).map(user-> {userRepository.deleteById(username);
        return username;}
        );
    }


    public Mono<UserDTO> addUser(UserDTO user){

        Mono<UserEntity> byId = userRepository.findById(user.getUserName());
        return byId
                .flatMap(existing ->
                        Mono.<UserDTO>error(new UserAlreadyExistException("user is already exist : " + user.getUserName()))
                )
                .switchIfEmpty(
                        roleRepository.save(RolesEntity.builder().authority("admin").role("read").build())
                                .flatMap(role->userRepository.existsById(user.getUserName()
                                        ).flatMap(exist->
                                                {
                                                    if (exist)
                                                         return userRepository.save(
                                                                UserEntity.builder()
                                                                        .userName(user.getUserName())
                                                                        .password(passwordEncoder.encode(user.getPassword()))
                                                                        .roleId(role.getRoleId())
                                                                        .enabled(true)
                                                                        .build()
                                                                );
                                                    else
                                                       return r2dbcEntityTemplate.insert(
                                                                UserEntity.builder()
                                                                        .userName(user.getUserName())
                                                                        .password(passwordEncoder.encode(user.getPassword()))
                                                                        .roleId(role.getRoleId())
                                                                        .enabled(true)
                                                                        .build()
                                                        );

                                                }


                                        )
                                )
                                .thenReturn(user)


                );
    }

    public Mono<UserDetails> updateUser(UserDTO user) {
        Mono<UserEntity> byId = userRepository.findById(user.getUserName());
        return byId.switchIfEmpty(Mono.error(()->{
            throw new UsernameNotFoundException("User is unauthorized : "+user.getUserName());
        })).map(user1->
                User.withUsername(user.getUserName()).password(user.getPassword()).roles("student").build()
        );
    }


}
