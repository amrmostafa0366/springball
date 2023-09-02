package com.game.football.service.imp;

import com.game.football.entity.League;
import com.game.football.entity.Player;
import com.game.football.entity.Team;
import com.game.football.error.NoTAcceptableException;
import com.game.football.repository.PlayerRepo;
import com.game.football.service.PlayerService;
import com.game.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PlayerServiceImp extends BaseServiceImp<Player, Long> implements PlayerService {
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private TeamService teamService;
    private static final String NUMBER_PATTERN = "^[0-9]+$";


    @Override
    public List<Player> findByTeamId(Long id) {
        Team team = teamService.findById(id);
        return playerRepo.findByTeam(team);
    }

    @Override
    public Player update(Long id, Player player) {
        Player dbPlayer = findById(id);

        if (player == null) {
            throw new NoTAcceptableException("Invalid Input");
        }
        if (player.getName() != null && !player.getName().isBlank()) {
            dbPlayer.setName(player.getName());
        }
        if (player.getPosition() != null && !player.getPosition().isBlank()) {
            dbPlayer.setPosition(player.getPosition());
        }
        if (isValidNumber(player.getNumber())) {
            dbPlayer.setNumber(player.getNumber());
        }
        return save(dbPlayer);
    }

    @Override
    public Player addToTeam(Long playerId, Long teamId) {
        Player player = findById(playerId);
        Team team = teamService.findById(teamId);
        player.setTeam(team);
        return save(player);
    }

    @Override
    public void updateAll() {
        String[] position = new String[]{"Striker", "Defender"};
        Random random = new Random();
        int counter = 1;
        for (long i = 1L; i <= 198; i++) {

            Player player = findById(i);
            player.setNumber(Long.toString(i));
            if (counter++ % 11 == 0) {
                counter = 1;
                player.setPosition("Goalkeeper");
            } else {
                player.setPosition(position[random.nextInt(position.length)]);
            }
            save(player);
        }
    }

    private boolean isValidNumber(String number) {
        if (number == null || number.isBlank()) {
            throw new NoTAcceptableException("Invalid Input");
        }
        return Pattern.compile(NUMBER_PATTERN).matcher(number).matches();
    }

}
