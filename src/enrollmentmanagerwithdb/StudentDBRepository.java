package enrollmentmanagerwithdb;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDBRepository {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

public List<Student> loadAll() {
    List<Student> students = new ArrayList<>();
    String sql = "SELECT * FROM EnrolledStudents ORDER BY id";            
    try (Connection conn = DbConnection.getConnection();
         Statement  stmt = conn.createStatement();
         ResultSet  rs   = stmt.executeQuery(sql)) {
        while (rs.next()) {
            students.add(mapRow(rs));
        }
        System.out.println("Loaded successfully"); 
    } catch (SQLException e) {
        System.err.println("Error loading: " + e.getMessage());
        e.printStackTrace();                         
    }
    return students;
}

    public void insert(Student s) {
        String sql = "INSERT INTO EnrolledStudents (name, course, email) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getCourse());
            ps.setString(3, s.getEmail());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                s.setId(keys.getInt(1));
            }

            Student inserted = findById(s.getId());
            if (inserted != null) {
                s.setDateCreated(inserted.getDateCreated());
                s.setDateUpdated(inserted.getDateUpdated());
            }

        } catch (SQLException e) {
            System.err.println("Error inserting: " + e.getMessage());
        }
    }

    public void update(Student s) {
        String sql = "UPDATE EnrolledStudents SET name=?, course=?, email=? WHERE id=?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getCourse());
            ps.setString(3, s.getEmail());
            ps.setInt   (4, s.getId());
            ps.executeUpdate();

            Student updated = findById(s.getId());
            if (updated != null) {
                s.setDateUpdated(updated.getDateUpdated());
            }

        } catch (SQLException e) {
            System.err.println("Error updating: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM EnrolledStudents WHERE id=?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting: " + e.getMessage());
        }
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM EnrolledStudents WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error finding student: " + e.getMessage());
        }
        return null;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("course"),
            rs.getString("email"),
            LocalDateTime.parse(rs.getString("dateCreated"), FORMATTER),
            LocalDateTime.parse(rs.getString("dateUpdated"), FORMATTER)
        );
    }
    
}