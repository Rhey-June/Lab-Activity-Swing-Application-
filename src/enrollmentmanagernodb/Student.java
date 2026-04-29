/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollmentmanagernodb;
import java.time.*;

/**
 *
 * @author Jhonrhey
 */
public class Student {

    private int           id;
    private String        name;
    private String        course;
    private String        email;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public Student(int id, String name, String course, String email,
                   LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id          = id;
        this.name        = name;
        this.course      = course;
        this.email       = email;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public int           getId()          { return id; }
    public String        getName()        { return name; }
    public String        getCourse()      { return course; }
    public String        getEmail()       { return email; }
    public LocalDateTime getDateCreated() { return dateCreated; }
    public LocalDateTime getDateUpdated() { return dateUpdated; }

    public void setId(int id)                   { this.id          = id; }
    public void setName(String name)            { this.name        = name; }
    public void setCourse(String course)        { this.course      = course; }
    public void setEmail(String email)          { this.email       = email; }
    public void setDateCreated(LocalDateTime d) { this.dateCreated = d; }
    public void setDateUpdated(LocalDateTime d) { this.dateUpdated = d; }
}
