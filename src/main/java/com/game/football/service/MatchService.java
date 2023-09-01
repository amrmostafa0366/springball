package com.game.football.service;

import com.game.football.entity.Match;
import com.game.football.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService extends BaseService<Match, Long> {

    List<Match> findByTeamId(Long id);
    Match play(Long leagueId);
    boolean playerKick(Long matchId, Long playerId);
}
