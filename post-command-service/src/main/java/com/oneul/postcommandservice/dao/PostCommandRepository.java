package com.oneul.postcommandservice.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneul.postcommandservice.domain.Post;

public interface PostCommandRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
    Optional<Post> findByIdAndWriter(Long id, Long writer);
    void deleteByIdAndWriter(Long id, Long writer);
    void deleteById(Long id);
    void delete(Post post);

    List<Post> findAllByExpiredAtLessThanAndDeletedAtIsNull(LocalDateTime expiredAt);  
}