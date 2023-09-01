package com.game.football.service;

import com.game.football.entity.Team;
import com.game.football.entity.dto.TeamWinnings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService extends BaseService<Team, Long> {
    List<Team> findByLeagueId(Long id);

    List<TeamWinnings> findRankingByLeagueId(Long id);

    void update(Long id, Team team);
}
