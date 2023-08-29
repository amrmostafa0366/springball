package com.game.football.service.imp;

import com.game.football.entity.League;
import com.game.football.entity.Referee;
import com.game.football.entity.Team;
import com.game.football.repository.TeamRepo;
import com.game.football.service.LeagueService;
import com.game.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImp extends BaseServiceImp<Team, Long> implements TeamService {
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private LeagueService leagueService;

    @Override
    public List<Team> findByLeagueId(Long id) {
        League league = leagueService.findById(id);
        return teamRepo.findByLeague(league);
    }
}
