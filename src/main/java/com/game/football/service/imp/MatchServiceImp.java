package com.game.football.service.imp;

import com.game.football.entity.Goal;
import com.game.football.entity.Match;
import com.game.football.entity.Player;
import com.game.football.entity.Team;
import com.game.football.repository.MatchRepo;
import com.game.football.service.MatchService;
import com.game.football.service.PlayerService;
import com.game.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MatchServiceImp extends BaseServiceImp<Match, Long> implements MatchService {
    @Autowired
    private MatchRepo matchRepo;
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    @Override
    public List<Match> findByTeamId(Long id) {
        Team team = teamService.findById(id);
        return matchRepo.findByTeam1OrTeam2(team, team);
    }

    @Override
    public Match play(Long leagueId) {
        List<Team> teams = teamService.findByLeagueId(leagueId);
        if (!teams.isEmpty() && teams.size() >= 2) {
            Random random = new Random();
            Team team1 = teams.get(random.nextInt(teams.size()));
            Team team2 = teams.get(random.nextInt(teams.size()));
            while (team1.getId() == team2.getId()) {
                team2 = teams.get(random.nextInt(teams.size()));
            }
            int score1 = random.nextInt(5);
            int score2 = random.nextInt(5);
            while (score1 == score2) {
                score2 = random.nextInt(5);
            }
            Team winner;
            if (score1 > score2) {
                winner = team1;
            } else {
                winner = team2;
            }

            Match match = new Match(team1, team2, score1, score2, winner);

            List<Player> team1players = playerService.findByTeamId(team1.getId());
            List<Player> team2players = playerService.findByTeamId(team2.getId());

            List<Goal> team1Goals = getGoalsPlayers(match, team1players, score1);
            List<Goal> team2Goals = getGoalsPlayers(match, team2players, score2);

            match.getGoals().addAll(team1Goals);
            match.getGoals().addAll(team2Goals);

            save(match);
            return match;
        }
        return null;
    }

    private List<Goal> getGoalsPlayers(Match match, List<Player> players, int goals) {
        Random random = new Random();
        List<Goal> list = new ArrayList<>();
        for (int i = 0; i < goals; i++) {
            Goal goal = new Goal(match, players.get(random.nextInt(players.size())));
            list.add(goal);
        }
        return list;
    }
}
