package com.game.football.controller;

import com.game.football.entity.League;
import com.game.football.service.LeagueService;
import jakarta.validation.Valid;
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
    public ResponseEntity<League> save(@Valid @RequestBody League league) {
        League result = leagueService.save(league);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<League> update(@PathVariable Long id, @Valid @RequestBody League league) {
        League result = leagueService.update(id, league);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
