package com.SA.serie_a.Repositories;

import com.SA.serie_a.Entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,String> {

    @Query(value = "select * from player_stats ps where ps.team = :team", nativeQuery = true)
    List<Player> findByTeam(@Param("team") String team);

    @Query(value = "select * from player_stats ps where ps.name = :name", nativeQuery = true)
    Player findByName(@Param("name") String name);

    @Query(value = "select * from player_stats ps where ps.role = :role", nativeQuery = true)
    List<Player> findByRole(@Param("role") String role);

    @Query(value = "select * from player_stats ps where ps.nation = :nation", nativeQuery = true)
    List<Player> findByNation(@Param("nation") String nation);
}
