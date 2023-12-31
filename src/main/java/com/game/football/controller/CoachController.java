package com.game.football.controller;

import com.game.football.entity.Coach;
import com.game.football.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {
    @Autowired
    private CoachService coachService;

    @GetMapping("")
    public ResponseEntity<List<Coach>> findAll() {
        List<Coach> result = coachService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/league/{id}")
    public ResponseEntity<List<Coach>> findByLeagueId(@PathVariable Long id) {
        List<Coach> result = coachService.findByLeagueId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> findById(@PathVariable Long id) {
        Coach result = coachService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Coach> save(@Valid @RequestBody Coach coach) {
        Coach result = coachService.save(coach);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> update(@PathVariable Long id, @Valid @RequestBody Coach coach) {
        Coach result = coachService.update(id, coach);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coachService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-to-team/{coachId}/{teamId}")
    public ResponseEntity<Coach> addToTeam(@PathVariable Long coachId, @PathVariable Long teamId) {
        Coach result = coachService.addToTeam(coachId, teamId);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-to-league/{coachId}/{leagueId}")
    public ResponseEntity<Coach> addToLeague(@PathVariable Long coachId, @PathVariable Long leagueId) {
        Coach result = coachService.addToLeague(coachId, leagueId);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
