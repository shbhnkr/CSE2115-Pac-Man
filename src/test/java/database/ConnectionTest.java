package database;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class ConnectionTest {

    @InjectMocks private transient DBconnection dbConnection;
    @Mock private transient Connection mockConnection;
    @Mock private transient Statement mockStatement;
    public transient Connection conn = null;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockDBConnection() throws Exception {
        String deleteQuery = String.format("DELETE FROM `login` WHERE `username`=?");
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
        try {
            conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO `login`(`username`, `password`) VALUES (?, ?)"));
            ps.setString(1, "avkvuadvvRVUVrvksdcvTECFkevSDcfcR");
            ps.setString(2, "Pan");
            int value = ps.executeUpdate();
            PreparedStatement deletePS = conn.prepareStatement(deleteQuery);
            deletePS.setString(1, "avkvuadvvRVUVrvksdcvTECFkevSDcfcR");
            Assertions.assertEquals(value, 1);
            Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
            deletePS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            conn.close();
        }

    }
}
