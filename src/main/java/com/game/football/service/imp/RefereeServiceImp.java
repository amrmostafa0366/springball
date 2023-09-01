package com.game.football.service.imp;

import com.game.football.entity.Referee;
import com.game.football.entity.League;
import com.game.football.error.NoTAcceptableException;
import com.game.football.repository.RefereeRepo;
import com.game.football.service.LeagueService;
import com.game.football.service.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefereeServiceImp extends BaseServiceImp<Referee, Long> implements RefereeService {
    @Autowired
    private RefereeRepo refereeRepo;
    @Autowired
    private LeagueService leagueService;

    @Override
    public void update(Long id, Referee referee) {
        Referee dbReferee = findById(id);
        if (referee != null && referee.getName() != null && !referee.getName().isBlank()) {
            dbReferee.setName(referee.getName());
            save(dbReferee);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }

    @Override
    public List<Referee> findByLeagueId(Long id) {
        League league = leagueService.findById(id);
        return refereeRepo.findByLeague(league);
    }

    @Override
    public void addToLeague(Long refereeId, Long leagueId) {
        Referee referee = findById(refereeId);
        League league = leagueService.findById(leagueId);
        referee.setLeague(league);
        save(referee);
    }

}
