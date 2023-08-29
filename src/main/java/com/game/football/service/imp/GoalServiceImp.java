package com.game.football.service.imp;

import com.game.football.entity.Goal;
import com.game.football.entity.Match;
import com.game.football.entity.Player;
import com.game.football.repository.GoalRepo;
import com.game.football.service.GoalService;
import com.game.football.service.MatchService;
import com.game.football.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImp extends BaseServiceImp<Goal, Long> implements GoalService {
    @Autowired
    private GoalRepo goalRepo;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private MatchService matchService;

    @Override
    public List<Goal> findByPlayerId(Long id) {
        Player player = playerService.findById(id);
        return goalRepo.findByPlayer(player);
    }

    @Override
    public List<Goal> findByMatchId(Long id) {
        Match match = matchService.findById(id);
        return goalRepo.findByMatch(match);
    }
}
