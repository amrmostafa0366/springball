package com.game.football.service;

import com.game.football.entity.Coach;
import com.game.football.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoachService extends BaseService<Coach, Long> {
    List<Coach> findByLeagueId(Long id);
}