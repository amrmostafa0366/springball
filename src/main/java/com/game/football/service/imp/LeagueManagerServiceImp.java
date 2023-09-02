package com.game.football.service.imp;

import com.game.football.entity.League;
import com.game.football.entity.LeagueManager;
import com.game.football.error.NoTAcceptableException;
import com.game.football.service.LeagueManagerService;
import com.game.football.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueManagerServiceImp extends BaseServiceImp<LeagueManager, Long> implements LeagueManagerService {

    @Autowired
    private LeagueService leagueService;

    @Override
    public LeagueManager update(Long id, LeagueManager leagueManager) {
        LeagueManager dbLeagueManager = findById(id);
        if (leagueManager != null && leagueManager.getName() != null && !leagueManager.getName().isBlank()) {
            dbLeagueManager.setName(leagueManager.getName());
            return save(dbLeagueManager);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }

    @Override
    public LeagueManager addToLeague(Long leagueManagerId, Long leagueId) {
        LeagueManager leagueManager = findById(leagueManagerId);
        League league = leagueService.findById(leagueId);
        leagueManager.setLeague(league);
        return save(leagueManager);
    }

}
