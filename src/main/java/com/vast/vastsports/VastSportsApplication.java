package com.vast.vastsports;

import com.vast.vastsports.db.PlayerRepository;
import com.vast.vastsports.db.TeamRepository;
import com.vast.vastsports.models.Player;
import com.vast.vastsports.models.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class VastSportsApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VastSportsApplication.class, args);
        PlayerRepository dbRepo = context.getBean(PlayerRepository.class);
        TeamRepository db2Repo = context.getBean(TeamRepository.class);

        List<Player> players = new ArrayList<>(List.of(
                new Player(1, "Kobe Bryant", 41, "SF", 20),
                new Player(2, "Jason Tatum", 25, "SF", 7),
                new Player(3, "Lebron James", 39, "SF", 21),
                new Player(4, "Stephen Curry", 35, "PG", 15),
                new Player(5, "Kevin Durant", 35, "PF", 16),
                new Player(6, "Luka Doncic", 24, "PG", 6),
                new Player(7, "Kawhi Leonard", 32, "SF", 13),
                new Player(8, "Russell Westbrook", 35, "PG", 16),
                new Player(9, "Paul George", 33, "SF", 14),
                new Player(10, "James Harden", 34, "SG", 15)

        ));

        dbRepo.saveAll(players);


        List<Team> teams = new ArrayList<>(List.of(
                new Team(1, 2024, "Atlanta Hawks", "ATL", false, 26.1, 26, 33, "State Farm Arena"),
                new Team(2, 2024, "Boston Celtics", "BOS", false, 28.4, 46, 12, "TD Garden"),
                new Team(3, 2024, "Brooklyn Nets", "BRK", false, 26.4, 23, 36, "Barclays Center"),
                new Team(4, 2024, "Chicago Bulls", "CHI", false, 28, 28, 31, "United Center"),
                new Team(5, 2024, "Charlotte Hornets", "CHO", false, 25.2, 15, 44, "Spectrum Center"),
                new Team(6, 2024, "Cleveland Cavaliers","CLE", false, 26.2, 38, 20, "Rocket Mortgage Fieldhouse"),
                new Team(7, 2024, "Dallas Mavericks", "DAL", false, 26.4, 34, 25, "American Airlines Center"),
                new Team(8, 2024, "Denver Nuggets", "DEN", false, 27, 41, 19, "Ball Arena"),
                new Team(9, 2024, "Detroit Pistons", "DET", false, 23.9, 9, 49, "Little Caesars Arena"),
                new Team(10, 2024, "Golden State Warriors", "GSW", false, 28.4, 31, 27, "Chase Center"),
                new Team(11, 2024, "Houston Rockets", "HOU", false, 25, 25, 34, "Toyota Center"),
                new Team(12, 2024, "Indiana Pacers", "IND", false, 25.3, 34, 26, "Gainbridge Fieldhouse"),
                new Team(13, 2024, "Los Angeles Clippers", "LAC", false, 30.7, 37, 20, "Crypto.com Arena"),
                new Team(14, 2024, "Los Angeles Lakers", "LAL", false, 27.9, 33, 28, "Crypto.com Arena"),
                new Team(15, 2024, "Memphis Grizzlies", "MEM", false, 24.7, 20, 39, "FedEx Forum"),
                new Team(16, 2024, "Miami Heat", "MIA", false, 28.1, 33, 26, "Kaseya Center"),
                new Team(17, 2024, "Milwaukee Bucks", "MIL", false, 30, 39, 21, "Fiserv Forum"),
                new Team(18, 2024, "Minnesota Timberwolves", "MIN", false, 27.2, 42, 17, "Target Center"),
                new Team(19, 2024, "New Orleans Pelicans", "NOP", false, 25.9, 35, 25, "Smoothie King Center"),
                new Team(20, 2024, "New York Knicks", "NYK", false, 26.3, 35, 25, "Madison Square Garden (IV)"),
                new Team(21, 2024, "Oklahoma City Thunder", "OKC", false, 23.2, 41, 18, "Paycom Center"),
                new Team(22, 2024, "Orlando Magic", "ORL", false, 23.9, 34, 26, "Amway Center"),
                new Team(23, 2024, "Philadelphia 76ers", "PHI", false, 28.2, 33, 25, "Wells Fargo Center"),
                new Team(24, 2024, "Phoenix Suns", "PHO", false, 29.1, 35, 24, "Footprint Center"),
                new Team(25, 2024, "Portland Trail Blazers", "POR", false, 24.5, 15, 42, "Moda Center"),
                new Team(26, 2024, "Sacramento Kings", "SAC", false, 26.5, 33, 25, "Golden 1 Center"),
                new Team(27, 2024, "San Antonio Spurs", "SAS", false, 23.1, 12, 48, "Frost Bank Center"),
                new Team(28, 2024, "Toronto Raptors", "TOR", false, 26.2, 22, 37, "Scotiabank Arena"),
                new Team(29, 2024, "Utah Jazz", "UTA", false, 25.7, 27, 33, "Delta Center"),
                new Team(30, 2024, "Washington Wizards", "WAS", false, 25.2, 9, 50, "Capital One Arena")

        ));

        db2Repo.saveAll(teams);

    }


}
