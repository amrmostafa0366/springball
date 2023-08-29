package com.game.football.repository;

import com.game.football.entity.League;
import com.game.football.entity.Referee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereeRepo extends BaseRepo<Referee, Long> {
    List<Referee> findByLeague(League league);
}
