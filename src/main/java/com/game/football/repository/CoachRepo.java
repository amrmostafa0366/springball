package com.game.football.repository;

import com.game.football.entity.Coach;
import com.game.football.entity.League;
import com.game.football.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepo extends BaseRepo<Coach, Long> {
    List<Coach> findByLeague(League league);
}
