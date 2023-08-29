package com.game.football.repository;

import com.game.football.entity.League;
import com.game.football.entity.Referee;
import com.game.football.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends BaseRepo<Team,Long>{
    List<Team> findByLeague(League league);
}
