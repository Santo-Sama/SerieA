package com.SA.serie_a.Services;

import com.SA.serie_a.Entities.Player;
import com.SA.serie_a.Repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getPlayer(){
        return playerRepository.findAll();
    }

    public List<Player> getPlayerByTeam(String teamName){
        return playerRepository.findByTeam(teamName);
    }

    public Optional<Player> getPlayerByName(String name){
        return Optional.of(playerRepository.findByName(name));
    }

    public List<Player> getPlayerByRole(String role){
        return playerRepository.findByRole(role);
    }

    public List<Player> getPlayerByNation(String nation){
        return playerRepository.findByNation(nation);
    }

    public Player addPlayer(Player player){
        return playerRepository.save(player);
    }

    public Optional<Player> updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer = playerRepository.findById(updatedPlayer.getName());

        if (existingPlayer.isPresent()){
            Player playerToUpdate = existingPlayer.get();
            // update field
            playerToUpdate.setAge(updatedPlayer.getAge());
            playerToUpdate.setAssist(updatedPlayer.getAssist());
            playerToUpdate.setGol(updatedPlayer.getGol());
            playerToUpdate.setRole(updatedPlayer.getRole());
            playerToUpdate.setTeam(updatedPlayer.getTeam());

            return Optional.of(playerRepository.save(playerToUpdate));
        }else {
            return Optional.empty();
        }
    }

    @Transactional
    public void deletePlayer(String playerName){
        playerRepository.deleteById(playerName);
    }

    public List<Player> getAllPlayer(){
        return playerRepository.findAll();
    }
}
