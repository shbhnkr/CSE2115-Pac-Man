package leaderboard;

import database.DBconnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class fromResultsetToStringTest {

    @Test
    public void covertTest() {
        assertEquals((DBconnection.prepQandToString.fromResultsetToString(
                DBconnection.prepQandToString.prepAndExecuteQuery(1))), "B 100");
    }
}
