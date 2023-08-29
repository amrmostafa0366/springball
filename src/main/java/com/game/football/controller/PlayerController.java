package com.game.football.controller;

import com.game.football.entity.Player;
import com.game.football.entity.Team;
import com.game.football.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @GetMapping("details/{id}")
//    public ResponseEntity<?> playerDetails(@PathVariable Long id){
//        // id , name , team , league , number of goals , team winnings
//    }
}
