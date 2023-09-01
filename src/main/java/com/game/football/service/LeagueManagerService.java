package com.game.football.service;

import com.game.football.entity.LeagueManager;
import org.springframework.stereotype.Service;

@Service
public interface LeagueManagerService extends BaseService<LeagueManager,Long> {
    void update(Long id, LeagueManager league);

    void addToLeague(Long leagueManagerId, Long leagueId);
}
