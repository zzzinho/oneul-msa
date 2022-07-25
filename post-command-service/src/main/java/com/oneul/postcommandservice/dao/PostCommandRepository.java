package com.oneul.postcommandservice.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneul.postcommandservice.domain.Post;

@Repository
public interface PostCommandRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
    Optional<Post> findByIdAndWriterId(Long id, Long writerId);
    void deleteByIdAndWriterId(Long id, Long userId);
    void deleteById(Long id);
    void delete(Post post);

    List<Post> findAllByExpiredAtLessThanAndDeletedAtIsNull(LocalDateTime expiredAt);  
}