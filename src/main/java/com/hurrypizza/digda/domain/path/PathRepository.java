package com.hurrypizza.digda.domain.path;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = """
                        INSERT INTO path(area, user_id)
                        VALUES (ST_POLYGONFROMTEXT(:area), :userId)
            """, nativeQuery = true)
    void saveArea(@Param("userId") Long userId, @Param("area") String area);

}
