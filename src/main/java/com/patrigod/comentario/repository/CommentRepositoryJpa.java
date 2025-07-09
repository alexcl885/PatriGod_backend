package com.patrigod.comentario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.comentario.entity.entity.Comment;

@Repository
public interface CommentRepositoryJpa extends JpaRepository<Comment,Long> {
    
}
