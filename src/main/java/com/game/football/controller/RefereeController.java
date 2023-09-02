package com.game.football.controller;

import com.game.football.entity.Referee;
import com.game.football.entity.Team;
import com.game.football.service.RefereeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referees")
public class RefereeController {
    @Autowired
    private RefereeService refereeService;

    @GetMapping("")
    public ResponseEntity<List<Referee>> findAll() {
        List<Referee> result = refereeService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/league/{id}")
    public ResponseEntity<List<Referee>> findByLeagueId(@PathVariable Long id) {
        List<Referee> result = refereeService.findByLeagueId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Referee> findById(@PathVariable Long id) {
        Referee result = refereeService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Referee> save(@Valid @RequestBody Referee referee) {
        Referee result = refereeService.save(referee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Referee> update(@PathVariable Long id, @Valid @RequestBody Referee referee) {
        Referee result = refereeService.update(id, referee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        refereeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{refereeId}/{leagueId}")
    public ResponseEntity<Referee> addToLeague(@PathVariable Long refereeId, @PathVariable Long leagueId) {
        Referee result = refereeService.addToLeague(refereeId, leagueId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
