package leaderboard;

import database.DBconnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FromResultsetToStringTest {

    @Test
    void covertTest() {
        assertEquals((DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(0))), "pacman   Score: 74874");
    }
}
