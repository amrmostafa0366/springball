package com.game.football.controller;

import com.game.football.entity.Goal;
import com.game.football.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {
    @Autowired
    private GoalService goalService;

    @GetMapping("")
    public ResponseEntity<List<Goal>> findAll() {
        List<Goal> result = goalService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/player/{id}")
    public ResponseEntity<List<Goal>> findByPlayerId(@PathVariable Long id) {
        List<Goal> result = goalService.findByPlayerId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/match/{id}")
    public ResponseEntity<List<Goal>> findByMatchId(@PathVariable Long id) {
        List<Goal> result = goalService.findByMatchId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> findById(@PathVariable Long id) {
        Goal result = goalService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
