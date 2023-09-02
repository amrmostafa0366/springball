package com.game.football.service.imp;

import com.game.football.entity.League;
import com.game.football.error.NoTAcceptableException;
import com.game.football.service.LeagueService;
import org.springframework.stereotype.Service;

@Service
public class LeagueServiceImp extends BaseServiceImp<League, Long> implements LeagueService {

    @Override
    public League update(Long id, League league) {
        League dbLeague = findById(id);
        if (league != null && league.getName() != null && !league.getName().isBlank()) {
            dbLeague.setName(league.getName());
            return save(dbLeague);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }
}
