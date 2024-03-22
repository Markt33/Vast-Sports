package com.vast.vastsports.services;

import com.vast.vastsports.db.PlayerRepository;
import com.vast.vastsports.models.Player;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

/**
 * @author Mark Tsvyan
 * @version 1.0
 */
@Service
public class PlayerService {
    private PlayerRepository repository;

    /**
     * @param repository this constructor connects to the PlayerRepo.
     */
    public PlayerService(PlayerRepository repository){
        this.repository = repository;
    }

    /**
     * @return this returns all the players
     */
    public List<Player> all(){
        List<Player> players = repository.findAll();
        return Collections.unmodifiableList(players);
    }

    /**
     * @param newPlayer adds a new player to the repo.
     * @return this returns the new player.
     */
    public Player addPlayer(Player newPlayer){
        return repository.save(newPlayer);

    }

    /**
     * @param updatePlayer updates the player info in the repo.
     * @param id this is the player's id.
     * @return returns the updated player info.
     */
    public Player updatePlayer(Player updatePlayer, int id) {
        Player currentPlayer = repository.findById(id).orElseThrow();

        currentPlayer.setId(updatePlayer.getId());
        currentPlayer.setName(updatePlayer.getName());
        currentPlayer.setAge(updatePlayer.getAge());
        currentPlayer.setPosition(updatePlayer.getPosition());
        currentPlayer.setExperience(updatePlayer.getExperience());

        return repository.save(currentPlayer);

    }

    /**
     * this deletes the player by id.
     * @param id is the player's id.
     */
    public void deletePlayer(int id){
        repository.deleteById(id);
    }

    /**
     * @param id is the player's id.
     * @return the player's id.
     */
    public Player byId(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * @param id checks the players id
     * @return this returns whether the player is there by the given id.
     */
    public boolean playerExistsByID(int id){
        return repository.findById(id).isPresent();
    }

    @Override
    public String toString() {
        return "PlayerService{" +
                "repository=" + repository +
                '}';
    }
}

