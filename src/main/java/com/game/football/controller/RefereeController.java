package com.game.football.controller;

import com.game.football.entity.Referee;
import com.game.football.entity.Team;
import com.game.football.service.RefereeService;
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
    public ResponseEntity<Void> save(@RequestBody Referee referee) {
        refereeService.save(referee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Referee referee, @PathVariable Long id) {
        refereeService.update(id, referee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        refereeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{refereeId}/{leagueId}")
    public ResponseEntity<Void> addToLeague(@PathVariable Long refereeId,@PathVariable Long leagueId){
        refereeService.addToLeague(refereeId,leagueId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
