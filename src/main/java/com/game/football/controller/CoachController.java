package com.game.football.controller;

import com.game.football.entity.Coach;
import com.game.football.service.CoachService;
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
    public ResponseEntity<Void> save(@RequestBody Coach coach) {
        coachService.save(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coachService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
