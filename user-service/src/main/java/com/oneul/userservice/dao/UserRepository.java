package com.oneul.userservice.dao;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oneul.userservice.domain.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    UserEntity save(UserEntity userEntity) throws DataIntegrityViolationException;
}
