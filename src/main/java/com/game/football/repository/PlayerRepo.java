package com.game.football.repository;

import com.game.football.entity.Player;
import com.game.football.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends BaseRepo<Player, Long> {
    List<Player> findByTeam(Team team);

}
