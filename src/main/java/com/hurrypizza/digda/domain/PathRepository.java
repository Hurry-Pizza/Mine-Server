package com.hurrypizza.digda.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PathRepository extends JpaRepository<Path, Long> {

    @Query(value = """
                SELECT ST_ASTEXT(area)
                FROM path 
                WHERE path_id = :id
            """, nativeQuery = true)
    Optional<String> findAreaById(@Param("id") Long id);

    @Query(value = """
                SELECT ST_ASTEXT(area) as area
                FROM path
            """, nativeQuery = true)
    List<String> findAllArea();


}
