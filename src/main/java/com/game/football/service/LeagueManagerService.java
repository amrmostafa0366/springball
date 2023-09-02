package com.game.football.service;

import com.game.football.entity.League;
import com.game.football.entity.LeagueManager;
import org.springframework.stereotype.Service;

@Service
public interface LeagueManagerService extends BaseService<LeagueManager,Long> {
    LeagueManager update(Long id, LeagueManager league);

    LeagueManager addToLeague(Long leagueManagerId, Long leagueId);
}
