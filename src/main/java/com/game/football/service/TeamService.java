package com.game.football.service;

import com.game.football.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService extends BaseService<Team, Long> {
    List<Team> findByLeagueId(Long id);
}
