package com.game.football.entity;

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
    private String time;

    public Goal() {
        time = new Random().nextInt(90) + ":" + new Random().nextInt(60);
    }

    public Goal(Match match, Player player, Long duration) {
        this();
        if (duration != null) {
            time = duration + ":" + new Random().nextInt(60);
        }
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
