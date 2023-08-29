package com.game.football.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @Column(name = "time")
    private StringBuilder time = new StringBuilder();

    public Goal() {
        time.append(new Random().nextInt(90));
        time.append(":");
        time.append(new Random().nextInt(60));
    }

    public Goal(Match match, Player player) {
        this();
        this.match = match;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public StringBuilder getTime() {
        return time;
    }

    public void setTime(StringBuilder time) {
        this.time = time;
    }
}
