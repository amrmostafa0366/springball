package com.game.football.service;

import com.game.football.entity.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService extends BaseService<Player, Long> {


    List<Player> findByTeamId(Long id);

    Player update(Long id, Player player);

    Player addToTeam(Long playerId, Long teamId);

    void updateAll();
}
