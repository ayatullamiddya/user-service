package com.prc.user_profile.repository;

import com.prc.user_profile.domain.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, String> {

}
