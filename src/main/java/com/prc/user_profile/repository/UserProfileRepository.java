package com.prc.user_profile.repository;

import com.prc.user_profile.domain.UserProfileEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends ReactiveCrudRepository<UserProfileEntity, String> {
}
