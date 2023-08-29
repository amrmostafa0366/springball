package com.game.football.service.imp;

import com.game.football.entity.Player;
import com.game.football.entity.Team;
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
}
