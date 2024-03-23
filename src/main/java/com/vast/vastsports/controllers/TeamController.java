package com.vast.vastsports.controllers;


import com.vast.vastsports.services.TeamService;
import com.vast.vastsports.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Mark Tsvyan
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/teams")
@CrossOrigin(origins = "*")
public class TeamController {

    private TeamService service;

    /**
     * @param service is the constructor that connects to the TeamService class.
     */
    public TeamController(TeamService service){
        this.service = service;
    }

    /**
     * @return this returns all the teams.
     */
    @GetMapping("all")
    public ResponseEntity<List<Team>> all(){
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    /**
     * @param id is the teams given id.
     * @return this returns all the teams.
     */
    @GetMapping("{id}")
    public ResponseEntity<Team> byId(@PathVariable int id){
        if (!service.teamExistsByID(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.byId(id), HttpStatus.OK);
    }

    /**
     * Adds a new team to the database.
     * @param newTeam the new team variable.
     * @return this returns the new team and HttpStatus.
     */
    @PostMapping("")
    public ResponseEntity<Team> addTeam(@RequestBody Team newTeam){
        return new ResponseEntity<>(service.addTeam(newTeam), HttpStatus.CREATED);
    }

    /**
     * @param id is the teams given id.
     * @param updateTeam the update team variable.
     * @return returns the updated player info.
     */
    @PutMapping("{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable int id,
                                           @RequestBody Team updateTeam){
        Team team = service.byId(id);
        if (team == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(service.updateTeam(updateTeam, id), HttpStatus.OK);
        }

    }

    /**
     * Deletes the Team by the given ID.
     * @param id is the teams given id.
     * @return this returns whether the team was deleted and the HttpStatus.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable int id){
        Team team = service.byId(id);
        if (team == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.deleteTeam(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public String toString() {
        return "TeamController{" +
                "service=" + service +
                '}';
    }
}
