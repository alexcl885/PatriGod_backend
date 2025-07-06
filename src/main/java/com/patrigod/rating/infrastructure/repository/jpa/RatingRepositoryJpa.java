package com.patrigod.rating.infrastructure.repository.jpa;

import java.util.Optional;

import com.patrigod.rating.infrastructure.repository.jpa.entity.RatingJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepositoryJpa extends JpaRepository<RatingJpa, Long> {

    Optional<RatingJpa> findByUser_IdAndArticle_Id(Long userId, Long articleId);

}
