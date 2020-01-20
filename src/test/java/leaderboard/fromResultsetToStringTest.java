package leaderboard;

import database.DBconnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class fromResultsetToStringTest {

    @Test
    public void covertTest() {
        assertEquals((DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(4))), "null 10 ");
    }
}
