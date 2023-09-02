package com.game.football.service.imp;

import com.game.football.entity.Goal;
import com.game.football.entity.Match;
import com.game.football.entity.Player;
import com.game.football.entity.Team;
import com.game.football.error.NotFoundException;
import com.game.football.repository.MatchRepo;
import com.game.football.service.GoalService;
import com.game.football.service.MatchService;
import com.game.football.service.PlayerService;
import com.game.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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
    @Autowired
    private GoalService goalService;

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

    @Override
    public boolean playerKick(Long matchId, Long playerId) {
        Match match = findById(matchId);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime matchStartTime = match.getDateTime();
        Duration elapsedDuration = Duration.between(matchStartTime, currentTime);
        long elapsedMinutes = elapsedDuration.toMinutes();

        if (elapsedMinutes > 91) {
            return false;
        }
        Player player = playerService.findById(playerId);
        if (player.getTeam() == match.getTeam1() || player.getTeam() == match.getTeam2()) {
            boolean isGoal = kick();
            if (isGoal) {
                if (player.getTeam() == match.getTeam1()) {
                    match.setScore1(match.getScore1() + 1);
                } else {
                    match.setScore2(match.getScore2() + 1);
                }

                updateWinner(match);

                Goal goal = new Goal(match, player, elapsedMinutes);
                goalService.save(goal);
            }
            return isGoal;
        } else {
            throw new NotFoundException("Player Is Not a Member Of Either Team");
        }
    }

    private void updateWinner(Match match) {
        if (match.getScore1() > match.getScore2()) {
            match.setWinner(match.getTeam1());
        } else if (match.getScore1() < match.getScore2()) {
            match.setWinner(match.getTeam2());
        } else {
            match.setWinner(null);
        }
    }

    private boolean kick() {
        return new Random().nextBoolean();
    }

    private List<Goal> getGoalsPlayers(Match match, List<Player> players, int goals) {
        Random random = new Random();
        List<Goal> list = new ArrayList<>();
        for (int i = 0; i < goals; i++) {
            Goal goal = new Goal(match, players.get(random.nextInt(players.size())), null);
            list.add(goal);
        }
        return list;
    }
}
