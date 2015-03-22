/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hfred
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    @Before
    public void setStats() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void findingPlayer() {
        Player player = stats.search("Semenko");
        assertTrue(player.getName().equals("Semenko"));
    }

    @Test
    public void playerNotFound() {
        Player player = stats.search("herpaderp");
        assertTrue(player == null);
    }

    @Test
    public void teamFoundAndCorrectSize() {
        List<Player> team = stats.team("EDM");
        assertTrue(team.size() == 3);
    }

    @Test
    public void teamNotFoundReturnsEmptyArray() {
        List<Player> team = stats.team("herpadurp");
        assertTrue(team.isEmpty());
    }

    @Test
    public void teamFoundContainsRightPlayers() {
        List<Player> team = stats.team("EDM");
        assertTrue(team.contains(stats.search("Semenko")));
        assertTrue(team.contains(stats.search("Kurri")));
        assertTrue(team.contains(stats.search("Gretzky")));
    }

    @Test
    public void topScorersReturnsEmptyWhenAskedZero() {
        List<Player> top = stats.topScorers(0);
        assertTrue(top.isEmpty());
    }

    @Test
    public void topScorersReturnsRightSizedArray() {
        List<Player> top = stats.topScorers(3);
        assertTrue(top.size() == 3);
    }

    @Test
    public void topScorerHasBiggestScore() {
        List<Player> top = stats.topScorers(4);
        assertTrue(top.get(0).equals(stats.search("Gretzky")));
    }

    @Test
    public void topScorersInRightOrder() {
        List<Player> top = stats.topScorers(5);
        assertTrue(top.get(0).equals(stats.search("Gretzky")));
        assertTrue(top.get(1).equals(stats.search("Lemieux")));
        assertTrue(top.get(2).equals(stats.search("Yzerman")));
        assertTrue(top.get(3).equals(stats.search("Kurri")));
        assertTrue(top.get(4).equals(stats.search("Semenko")));
    }

}
