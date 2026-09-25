package com.prc.user_profile.service;

import com.prc.user_profile.model.UserProfileDTO;
import com.prc.user_profile.model.UserSignupDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserProfileService {
   public Flux<UserProfileDTO> getAllUserProfiles();
   public Mono<UserProfileDTO> getUserProfileById(String username);
   public Mono<UserProfileDTO> addUserProfile(UserSignupDTO user);
}
