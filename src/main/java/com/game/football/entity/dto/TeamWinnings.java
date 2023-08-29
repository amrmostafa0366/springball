package com.game.football.entity.dto;

import com.game.football.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamWinnings {
    private Team team;
    private int winnings;

    public TeamWinnings() {
    }

    public TeamWinnings(Team team, int winnings) {
        this.team = team;
        this.winnings = winnings;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getWinnings() {
        return winnings;
    }

    public void setWinnings(int winnings) {
        this.winnings = winnings;
    }
}
