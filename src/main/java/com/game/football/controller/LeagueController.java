package com.game.football.controller;

import com.game.football.entity.League;
import com.game.football.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    @GetMapping("")
    public ResponseEntity<List<League>> findAll() {
        List<League> result = leagueService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> findById(@PathVariable Long id) {
        League result = leagueService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody League league) {
        leagueService.save(league);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody League league, @PathVariable Long id) {
        leagueService.update(id,league);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
