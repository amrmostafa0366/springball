package com.game.football.service.imp;

import com.game.football.entity.Coach;
import com.game.football.entity.League;
import com.game.football.entity.Team;
import com.game.football.error.ConflictException;
import com.game.football.error.NoTAcceptableException;
import com.game.football.repository.CoachRepo;
import com.game.football.service.CoachService;
import com.game.football.service.LeagueService;
import com.game.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CoachServiceImp extends BaseServiceImp<Coach, Long> implements CoachService {
    @Autowired
    private CoachRepo coachRepo;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private TeamService teamService;

    @Override
    public Coach update(Long id, Coach coach) {
        Coach dbCoach = findById(id);
        if (coach != null && coach.getName() != null && !coach.getName().isBlank()) {
            dbCoach.setName(coach.getName());
            return save(dbCoach);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }

    @Override
    public List<Coach> findByLeagueId(Long id) {
        League league = leagueService.findById(id);
        return coachRepo.findByLeague(league);
    }

    @Override
    public Coach findByTeamId(Long id) {
        Team team = teamService.findById(id);
        return coachRepo.findByTeam(team);
    }

    @Override
    public Coach addToLeague(Long coachId, Long leagueId) {
        Coach coach = findById(coachId);
        League league = leagueService.findById(leagueId);
        if (coach.getTeam() != null) {
            coach.getTeam().setLeague(league);
        }
        coach.setLeague(league);
        return save(coach);
    }


    @Override
    public Coach addToTeam(Long coachId, Long teamId) {
        Coach coach = findById(coachId);
        Team team = teamService.findById(teamId);
        if (findByTeam(team) != null) {
            throw new ConflictException("This Team Already Have A Coach");
        }
        coach.setTeam(team);
        if (team.getLeague() != null) {
            coach.setLeague(team.getLeague());
        }
        return save(coach);
    }

    private Coach findByTeam(Team team) {
        return coachRepo.findByTeam(team);
    }
}
