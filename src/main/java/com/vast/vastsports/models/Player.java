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
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int season;
    private String name;
    private String position;
    private int age;
    private int experience;
    private String league;
    private String team;
    private int assist;
    private int steals;
    private int blocks;
    private int tov;
    private int points;



}

