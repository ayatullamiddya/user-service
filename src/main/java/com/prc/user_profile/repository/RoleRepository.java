package com.prc.user_profile.repository;

import com.prc.user_profile.domain.RolesEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<RolesEntity,Integer> {
}
