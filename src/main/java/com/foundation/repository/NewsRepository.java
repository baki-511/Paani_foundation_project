package com.foundation.repository;

import com.foundation.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findNewsByTitle(String title);
}
