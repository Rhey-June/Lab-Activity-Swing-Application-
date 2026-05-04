package enrollmentmanagerwithdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static final String DB_URL = "jdbc:sqlite:EnrolledStudentsDB.db";

    public static Connection getConnection() throws SQLException {
        System.out.println("getConnection() called");
        return DriverManager.getConnection(DB_URL);
    }   

    private static void initDatabase() {
        System.out.println("initDatabase() called");
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement  stmt = conn.createStatement()) {

            System.out.println("DB location: " +
                new java.io.File("EnrolledStudentsDB.db").getAbsolutePath());

            stmt.execute(
                "CREATE TABLE IF NOT EXISTS EnrolledStudents ("
              + "id          INTEGER PRIMARY KEY AUTOINCREMENT, "
              + "name        TEXT    NOT NULL, "
              + "course      TEXT    NOT NULL, "
              + "email       TEXT, "
              + "dateCreated TEXT    DEFAULT (datetime('now', 'localtime')), "
              + "dateUpdated TEXT    DEFAULT (datetime('now', 'localtime'))"
              + ")"
            );
            System.out.println("Table ready");

            stmt.execute(
                "CREATE TRIGGER IF NOT EXISTS update_dateUpdated "
              + "AFTER UPDATE ON EnrolledStudents "
              + "FOR EACH ROW "
              + "BEGIN "
              + "    UPDATE EnrolledStudents "
              + "    SET dateUpdated = datetime('now', 'localtime') "
              + "    WHERE id = OLD.id; "
              + "END"
            );
            System.out.println("Trigger ready");

        } catch (SQLException e) {
            System.err.println("initDatabase failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}