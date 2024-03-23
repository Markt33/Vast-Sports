package com.vast.vastsports.services;

import com.vast.vastsports.models.Team;
import com.vast.vastsports.db.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mark Tsvyan
 * @version 1.0
 */
@Service
public class TeamService {

    private TeamRepository repository;

    /**
     * @param repository this constructor connects to the TeamRepo.
     */
    public TeamService(TeamRepository repository){
        this.repository = repository;
    }

    /**
     * @return this returns the list of all teams.
     */
    public List<Team> all(){
        List<Team> teams = repository.findAll();
        return Collections.unmodifiableList(teams);
    }

    /**
     * @param team adds the teams to the repo.
     * @return this returns the new team.
     */
    public Team addTeam(Team team){
        return repository.save(team);
    }

    /**
     * @param updateTeam this updates the team.
     * @param id this is the player's id.
     * @return returns the updated player info.
     */
    public Team updateTeam(Team updateTeam, int id){
        Team currentTeam = repository.findById(id).orElseThrow();
        currentTeam.setId(updateTeam.getId());
        currentTeam.setTeamName(updateTeam.getTeamName());
        currentTeam.setPlayoffs(updateTeam.isPlayoffs());
        currentTeam.setSeason(updateTeam.getSeason());
        currentTeam.setWins(updateTeam.getWins());

        return repository.save(currentTeam);

    }

    /**
     * this deletes the player by id.
     * @param id is the player's id.
     */
    public void deleteTeam(int id){
        repository.deleteById(id);
    }

    /**
     * @param id is the player's id.
     * @return returns the team by their id.
     */
    public Team byId(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * @param id this is the team's id.
     * @return this returns whether the team is found by the given id.
     */
    public boolean teamExistsByID(int id){
        return repository.findById(id).isPresent();
    }

    @Override
    public String toString() {
        return "TeamService{" +
                "repository=" + repository +
                '}';
    }
}

