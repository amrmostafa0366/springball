package com.game.football.repository;

import com.game.football.entity.Match;
import com.game.football.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends BaseRepo<Match, Long> {
    List<Match> findByTeam1OrTeam2(Team team1, Team team2);
}
