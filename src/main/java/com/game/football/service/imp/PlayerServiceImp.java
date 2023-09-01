package com.game.football.service.imp;

import com.game.football.entity.League;
import com.game.football.entity.Player;
import com.game.football.entity.Team;
import com.game.football.error.NoTAcceptableException;
import com.game.football.repository.PlayerRepo;
import com.game.football.service.PlayerService;
import com.game.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImp extends BaseServiceImp<Player, Long> implements PlayerService {
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private TeamService teamService;

    @Override
    public List<Player> findByTeamId(Long id) {
        Team team = teamService.findById(id);
        return playerRepo.findByTeam(team);
    }

    @Override
    public void update(Long id, Player player) {
        Player dbPlayer = findById(id);
        if (player != null && player.getName() != null && !player.getName().isBlank()) {
            dbPlayer.setName(player.getName());
            save(dbPlayer);
        } else {
            throw new NoTAcceptableException("Invalid Input");
        }
    }

    @Override
    public void addToTeam(Long playerId, Long teamId) {
        Player player = findById(playerId);
        Team team = teamService.findById(teamId);
        player.setTeam(team);
        save(player);
    }
}
