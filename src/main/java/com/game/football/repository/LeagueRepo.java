package com.game.football.repository;

import com.game.football.entity.League;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepo extends BaseRepo<League, Long> {
}
