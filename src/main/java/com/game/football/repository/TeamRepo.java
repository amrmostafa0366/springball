package com.game.football.repository;

import com.game.football.entity.League;
import com.game.football.entity.Referee;
import com.game.football.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends BaseRepo<Team, Long> {
    List<Team> findByLeague(League league);

    @Query("SELECT t, COUNT(m) " +
            "FROM Team t " +
            "JOIN Match m ON (t = m.team1 OR t = m.team2) " +
            "WHERE t.league = :league " +
            "  AND m.winner = t " +
            "GROUP BY t " +
            "ORDER BY COUNT(m) DESC")
    List<Object[]> findRankingByLeague(@Param("league") League league);

}
