package com.game.football.repository;

import com.game.football.entity.League;
import com.game.football.entity.LeagueManager;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueManagerRepo extends BaseRepo<LeagueManager, Long> {
    LeagueManager findByLeague(League league);
}
