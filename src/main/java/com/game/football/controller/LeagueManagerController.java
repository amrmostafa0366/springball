package com.game.football.controller;

import com.game.football.entity.LeagueManager;
import com.game.football.service.LeagueManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues-managers")
public class LeagueManagerController {
    @Autowired
    private LeagueManagerService leagueManagerService;

    @GetMapping("")
    public ResponseEntity<List<LeagueManager>> findAll() {
        List<LeagueManager> result = leagueManagerService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueManager> findById(@PathVariable Long id) {
        LeagueManager result = leagueManagerService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<LeagueManager> save(@Valid @RequestBody LeagueManager league) {
        LeagueManager result = leagueManagerService.save(league);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeagueManager> update(@PathVariable Long id, @Valid @RequestBody LeagueManager league) {
        LeagueManager result = leagueManagerService.update(id, league);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueManagerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{leagueManagerId}/{leagueId}")
    public ResponseEntity<LeagueManager> addToLeague(@PathVariable Long leagueManagerId, @PathVariable Long leagueId) {
        LeagueManager result = leagueManagerService.addToLeague(leagueManagerId, leagueId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
