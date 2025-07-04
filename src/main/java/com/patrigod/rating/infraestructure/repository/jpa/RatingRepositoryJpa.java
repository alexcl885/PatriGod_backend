package com.patrigod.rating.infraestructure.repository.jpa;

import java.util.Optional;

import com.patrigod.rating.infraestructure.repository.jpa.entity.RatingJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepositoryJpa extends JpaRepository<RatingJpa, Long> {
    
    Optional<RatingJpa> findByUsuarioIdAndArticle(Long userId, Long articleId);

}
