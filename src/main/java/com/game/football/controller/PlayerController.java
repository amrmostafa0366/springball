package com.game.football.controller;

import com.game.football.entity.Player;

import com.game.football.entity.Team;
import com.game.football.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("")
    public ResponseEntity<List<Player>> findAll() {
        List<Player> result = playerService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<List<Player>> findByTeamId(@PathVariable Long id) {
        List<Player> result = playerService.findByTeamId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        Player result = playerService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Player> save(@Valid @RequestBody Player player) {
        Player result = playerService.save(player);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @Valid @RequestBody Player player) {
        Player result = playerService.update(id, player);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{playerId}/{teamId}")
    public ResponseEntity<Player> addToTeam(@PathVariable Long playerId, @PathVariable Long teamId) {
        Player result = playerService.addToTeam(playerId, teamId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
//    @PutMapping("update-all")
//    public ResponseEntity<Void> updateAllPlayers(){
//        playerService.updateAll();
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
