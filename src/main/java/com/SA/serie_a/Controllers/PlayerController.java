package com.SA.serie_a.Controllers;

import com.SA.serie_a.Entities.Player;
import com.SA.serie_a.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/all-player")
    public ResponseEntity<List<Player>> playerList(){
        return ResponseEntity.ok(playerService.getAllPlayer());
    }

    @GetMapping(path = "/{playerName}")
    public ResponseEntity<Player> findByName(@PathVariable String playerName){
        Optional<Player> optionalPlayer = playerService.getPlayerByName(playerName);
        if (optionalPlayer.isPresent()){
            return ResponseEntity.ok(optionalPlayer.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/new-player")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        Player playerAdd = playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerAdd);
    }

    @PutMapping("modify/{playerName}")
    public ResponseEntity<Player> modifyPlayer(@PathVariable String playerName, @RequestBody Player player){
        Optional<Player> optionalPlayer = playerService.updatePlayer(player);
        if (optionalPlayer.isPresent()){
            return ResponseEntity.ok(optionalPlayer.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("player delete", HttpStatus.OK);
    }
}
