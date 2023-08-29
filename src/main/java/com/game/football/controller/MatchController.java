package com.game.football.controller;

import com.game.football.entity.Match;
import com.game.football.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("")
    public ResponseEntity<List<Match>> findAll() {
        List<Match> result = matchService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/team/{id}")
    public ResponseEntity<List<Match>> findByTeamId(@PathVariable Long id) {
        List<Match> result = matchService.findByTeamId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> findById(@PathVariable Long id) {
        Match result = matchService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/play/{leagueId}")
    public ResponseEntity<Match> play(@PathVariable Long leagueId) {
        Match result = matchService.play(leagueId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
