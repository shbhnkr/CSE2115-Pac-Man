package leaderboard;

import database.DBconnection;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FromResultsetToStringTest {

    @Test
    void covertTest() {
        assertEquals((DBconnection.fromResultsetToString(
                Objects.requireNonNull(DBconnection.prepAndExecuteQuery(0)))), "pacman   Score: 74874");
    }
}
