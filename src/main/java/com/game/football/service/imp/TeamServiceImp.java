package com.game.football.service.imp;

import com.game.football.entity.League;
import com.game.football.entity.Team;
import com.game.football.entity.dto.TeamWinnings;
import com.game.football.error.NoTAcceptableException;
import com.game.football.repository.TeamRepo;
import com.game.football.service.LeagueService;
import com.game.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImp extends BaseServiceImp<Team, Long> implements TeamService {
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private LeagueService leagueService;

    @Override
    public void update(Long id, Team team) {
        Team dbTeam = findById(id);
        if (team != null && team.getName() != null && !team.getName().isBlank()) {
            dbTeam.setName(team.getName());
            save(dbTeam);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }

    @Override
    public List<Team> findByLeagueId(Long id) {
        League league = leagueService.findById(id);
        return teamRepo.findByLeague(league);
    }

    @Override
    public List<TeamWinnings> findRankingByLeagueId(Long id) {
        League league = leagueService.findById(id);
        List<Object[]> results = teamRepo.findRankingByLeague(league);
        List<TeamWinnings> teamWinningsList = new ArrayList<>();

        for (Object[] result : results) {
            Team team = (Team) result[0];
            Long count = (Long) result[1];
            teamWinningsList.add(new TeamWinnings(team, count.intValue()));
        }

        return teamWinningsList;
    }
}
