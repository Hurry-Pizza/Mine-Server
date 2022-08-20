package com.hurrypizza.digda.domain.path;

import com.hurrypizza.digda.domain.projection.PathUserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PathAreaRepository extends JpaRepository<Path, Long> {

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

    @Query(value = """
                SELECT  p.path_id as pathId,
                        ST_ASTEXT(p.area) as area,
                        u.user_id as userId,
                        u.color as color
                FROM path as p
                INNER JOIN user as u ON p.user_id = u.user_id
                WHERE ST_Within(p.area, ST_POLYGONFROMTEXT(:currentMap))
            """, nativeQuery = true)
    List<PathUserProjection> findAllPathWithin(@Param("currentMap") String currentMap);

    @Modifying
    @Query(value = """
                INSERT INTO path(area, user_id)
                VALUES (ST_POLYGONFROMTEXT(:area), :userId)
            """, nativeQuery = true)
    void saveArea(@Param("userId") Long userId, @Param("area") String area);

}
