package com.vast.vastsports.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mark Tsvyan
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int season;
    private String teamName;
    private String teamABV;
    private boolean playoffs;
    private double avgAge;
    private int wins;
    private int losses;
    private String arenaName;


}


