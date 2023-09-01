package com.game.football.service;

import com.game.football.entity.Referee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RefereeService extends BaseService<Referee, Long> {
    void update(Long id, Referee referee);

    List<Referee> findByLeagueId(Long id);

    void addToLeague(Long refereeId, Long leagueId);
}
