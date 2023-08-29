package com.game.football.repository;

import com.game.football.entity.Goal;
import com.game.football.entity.Match;
import com.game.football.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepo extends BaseRepo<Goal, Long> {
    List<Goal> findByPlayer(Player player);

    List<Goal> findByMatch(Match match);
}
