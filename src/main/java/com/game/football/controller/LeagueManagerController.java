package com.game.football.controller;

import com.game.football.entity.LeagueManager;
import com.game.football.service.LeagueManagerService;
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
    public ResponseEntity<Void> save(@RequestBody LeagueManager league) {
        leagueManagerService.save(league);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody LeagueManager league, @PathVariable Long id) {
        leagueManagerService.update(id,league);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueManagerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
