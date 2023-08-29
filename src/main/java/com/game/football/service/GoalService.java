package com.game.football.service;

import com.game.football.entity.Goal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoalService extends BaseService<Goal, Long> {
    List<Goal> findByPlayerId(Long id);

    List<Goal> findByMatchId(Long id);
}
