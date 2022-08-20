package com.hurrypizza.mine.domain.path;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PathRepository extends JpaRepository<Path, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<Path> findAllByCreatedAtGreaterThanEqual(LocalDateTime criteriaDateTime);

}
