package com.hurrypizza.mine.domain.path;

import com.hurrypizza.mine.domain.projection.PathUserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PathRouteRepository extends JpaRepository<Path, Long> {

    @Query(value = """
                SELECT ST_ASTEXT(route)
                FROM path 
                WHERE path_id = :id
            """, nativeQuery = true)
    Optional<String> findRouteById(@Param("id") Long id);

    @Query(value = """
                SELECT ST_ASTEXT(route) as route
                FROM path
            """, nativeQuery = true)
    List<String> findAllRoute();

    @Query(value = """
                SELECT  p.path_id as pathId,
                        ST_ASTEXT(p.route) as route,
                        u.user_id as userId,
                        u.nickname as userNickname,
                        u.color as color
                FROM path as p
                INNER JOIN user as u ON p.user_id = u.user_id
                WHERE ST_Within(p.route, ST_POLYGONFROMTEXT(:currentMap))
            """, nativeQuery = true)
    List<PathUserProjection> findAllPathWithin(@Param("currentMap") String currentMap);

    @Modifying
    @Query(value = """
                INSERT INTO path(route, user_id, area)
                VALUES (ST_POLYGONFROMTEXT(:route), :userId, :area)
            """, nativeQuery = true)
    void save(@Param("userId") Long userId,
              @Param("route") String route,
              @Param("area") Double area);

}
