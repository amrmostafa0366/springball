package com.game.football.service.imp;

import com.game.football.entity.Coach;
import com.game.football.entity.League;
import com.game.football.entity.Team;
import com.game.football.error.NoTAcceptableException;
import com.game.football.repository.CoachRepo;
import com.game.football.service.CoachService;
import com.game.football.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CoachServiceImp extends BaseServiceImp<Coach, Long> implements CoachService {
    @Autowired
    private CoachRepo coachRepo;
    @Autowired
    private LeagueService leagueService;

    @Override
    public void update(Long id, Coach coach) {
        Coach dbCoach = findById(id);
        if (coach != null && coach.getName() != null && !coach.getName().isBlank()) {
            dbCoach.setName(coach.getName());
            save(dbCoach);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }
    @Override
    public List<Coach> findByLeagueId(Long id) {
        League league = leagueService.findById(id);
        return coachRepo.findByLeague(league);
    }

}
