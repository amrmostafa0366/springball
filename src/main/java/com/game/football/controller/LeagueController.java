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

    @GetMapping
    public ResponseEntity<List<League>> findAll() {
        List<League> leagues = leagueService.findAll();
        return ResponseEntity.ok(leagues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> findById(@PathVariable Long id) {
        League league = leagueService.findById(id);
        if (league != null) {
            return ResponseEntity.ok(league);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<League> save(@Valid @RequestBody League league) {
        League result = leagueService.save(league);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<League> update(@PathVariable Long id, @Valid @RequestBody League league) {
        League result = leagueService.update(id, league);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = leagueService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
