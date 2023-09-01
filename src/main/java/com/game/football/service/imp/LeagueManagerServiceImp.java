package com.game.football.service.imp;

import com.game.football.entity.LeagueManager;
import com.game.football.error.NoTAcceptableException;
import com.game.football.service.LeagueManagerService;
import org.springframework.stereotype.Service;

@Service
public class LeagueManagerServiceImp extends BaseServiceImp<LeagueManager, Long> implements LeagueManagerService {
    @Override
    public void update(Long id, LeagueManager leagueManager) {
        LeagueManager dbLeagueManager = findById(id);
        if (leagueManager != null && leagueManager.getName() != null && !leagueManager.getName().isBlank()) {
            dbLeagueManager.setName(leagueManager.getName());
            save(dbLeagueManager);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }
}
