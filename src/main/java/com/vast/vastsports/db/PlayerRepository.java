package com.vast.vastsports.db;

import com.vast.vastsports.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mark Tsvyan
 * @version 1.0
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
