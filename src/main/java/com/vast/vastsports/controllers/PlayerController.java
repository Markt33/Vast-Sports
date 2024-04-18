package com.vast.vastsports.controllers;

import com.vast.vastsports.models.Player;
import com.vast.vastsports.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the PlayerController class. It maps the player data.
 * @author Mark Tsvyan
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/players")
@CrossOrigin(origins = "*")
public class PlayerController {

    private PlayerService service;

    /**
     * @param service is the constructor that connects to the PlayerService class.
     */
    public PlayerController(PlayerService service){
        this.service = service;
    }

    /**
     * Gets all the players.
     * @return this returns all the players.
     */
    @GetMapping("all")
    public ResponseEntity<List<Player>> all(){
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    /**
     * @param id is the players given id.
     * @return returns the player with the given id.
     */
    @GetMapping("{id}")
    public ResponseEntity<Player> byId(@PathVariable int id){
        if (!service.playerExistsByID(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.byId(id), HttpStatus.OK);
    }

    /**
     * Adds a new player to the database.
     * @param newPlayer the new player variable.
     * @return this returns the new player and HttpStatus
     */
    @PostMapping("")
    public ResponseEntity<Player> addPlayer(@RequestBody Player newPlayer){
        return new ResponseEntity<>(service.addPlayer(newPlayer), HttpStatus.CREATED);
    }

    /**
     * @param id is the players given id.
     * @param updatePlayer the update player variable
     * @return returns the updated player info.
     */
    @PutMapping("{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id,
                                               @RequestBody Player updatePlayer){
        Player player = service.byId(id);
        if (player == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(service.updatePlayer(updatePlayer, id), HttpStatus.OK);
        }

    }

    /**
     * Deletes the Player by the given ID.
     * @param id is the players given id.
     * @return this returns the HttpsStatus error
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable int id) {
        Player player = service.byId(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.deletePlayer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public String toString() {
        return "PlayerController{" +
                "service=" + service +
                '}';
    }
}

