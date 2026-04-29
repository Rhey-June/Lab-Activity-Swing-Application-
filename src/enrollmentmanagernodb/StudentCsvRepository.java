package enrollmentmanagernodb;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jhonrhey
 */
public class StudentCsvRepository {
    private static final String FILE_PATH  = "data/students.csv";
    private static final String CSV_HEADER = "ID,Name,Course,Email,DateCreated,DateUpdated";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void ensureFileExists() throws IOException {
        File file = new File(FILE_PATH);
        File dir  = file.getParentFile();
        if (dir != null && !dir.exists()) dir.mkdirs();
        if (!file.exists()) {
            file.createNewFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(CSV_HEADER);
                bw.newLine();
            }
        }
    }

    public List<Student> loadAll() throws IOException {
        ensureFileExists();
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }
                line = line.trim();
                if (line.isEmpty()) continue;
                Student s = parseLine(line);
                if (s != null) students.add(s);
            }
        }
        return students;
    }

    public void saveAll(List<Student> students) throws IOException {
        ensureFileExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            bw.write(CSV_HEADER);
            bw.newLine();
            for (Student s : students) {
                bw.write(toCSVLine(s));
                bw.newLine();
            }
        }
    }

    private Student parseLine(String line) {
        String[] parts = splitCSV(line);
        if (parts.length < 6) return null;
        try {
            int           id          = Integer.parseInt(parts[0].trim());
            String        name        = parts[1].trim();
            String        course      = parts[2].trim();
            String        email       = parts[3].trim();
            LocalDateTime dateCreated = LocalDateTime.parse(parts[4].trim(), FORMATTER);
            LocalDateTime dateUpdated = LocalDateTime.parse(parts[5].trim(), FORMATTER);
            return new Student(id, name, course, email, dateCreated, dateUpdated);
        } catch (Exception e) {
            return null;
        }
    }

    private String toCSVLine(Student s) {
        return s.getId()                                    + ","
             + escape(s.getName())                          + ","
             + escape(s.getCourse())                        + ","
             + escape(s.getEmail())                         + ","
             + escape(s.getDateCreated().format(FORMATTER)) + ","
             + escape(s.getDateUpdated().format(FORMATTER));
    }

    private String escape(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    private String[] splitCSV(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    sb.append('"'); i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        return tokens.toArray(new String[0]);
    }
}