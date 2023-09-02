package com.game.football.controller;

import com.game.football.entity.League;
import com.game.football.entity.Team;
import com.game.football.entity.dto.TeamWinnings;
import com.game.football.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("")
    public ResponseEntity<List<Team>> findAll() {
        List<Team> result = teamService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/league/{id}")
    public ResponseEntity<List<Team>> findByLeagueId(@PathVariable Long id) {
        List<Team> result = teamService.findByLeagueId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("ranking/{id}")
    public ResponseEntity<List<TeamWinnings>> findRankingByLeagueId(@PathVariable Long id) {
        List<TeamWinnings> result = teamService.findRankingByLeagueId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        Team result = teamService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Team> save(@Valid @RequestBody Team team) {
        Team result = teamService.save(team);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable Long id, @Valid @RequestBody Team team) {
        Team result = teamService.update(id, team);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{teamId}/{leagueId}")
    public ResponseEntity<Team> addToLeague(@PathVariable Long teamId, @PathVariable Long leagueId) {
        Team result = teamService.addToLeague(teamId, leagueId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
