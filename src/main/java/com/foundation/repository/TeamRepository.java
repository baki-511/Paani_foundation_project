package com.foundation.repository;

import com.foundation.entity.Blog;
import com.foundation.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
