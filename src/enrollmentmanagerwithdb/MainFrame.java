package enrollmentmanagerwithdb; 

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(MainFrame.class.getName());

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final List<Student>       students   = new ArrayList<>();
    
    private final StudentDBRepository repository = new StudentDBRepository();
    
    private int nextId = 1;

    public MainFrame() {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) tableStudents.getModel();
        model.setColumnIdentifiers(new String[]{
            "ID", "Name", "Course", "Email", "DateCreated", "DateUpdated"
        });
        tfName.setText("");
        tfEmail.setText("");
        loadFromDB();
        refreshTableFromList();
        setNextId();
        showCard("list");
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        sidebarPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnEnrollling = new javax.swing.JButton();
        btnList = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        cardList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStudents = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnDeleteSelected = new javax.swing.JButton();
        btnUpdateSelected = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        cardAdd = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jlName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcbCourse = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Enrollment Manager");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Student Enrollment Manager");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(310, 10, 340, 40);

        sidebarPanel.setBackground(new java.awt.Color(30, 41, 59));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dashboard");
        jLabel3.setFocusable(false);

        btnEnrollling.setBackground(new java.awt.Color(225, 214, 196));
        btnEnrollling.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEnrollling.setForeground(new java.awt.Color(0, 0, 0));
        btnEnrollling.setText("Add Student");
        btnEnrollling.addActionListener(this::btnEnrolllingActionPerformed);

        btnList.setBackground(new java.awt.Color(225, 214, 196));
        btnList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnList.setForeground(new java.awt.Color(0, 0, 0));
        btnList.setText("List of Students");
        btnList.addActionListener(this::btnListActionPerformed);

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEnrollling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(50, 50, 50)
                .addComponent(btnEnrollling)
                .addGap(46, 46, 46)
                .addComponent(btnList)
                .addContainerGap(284, Short.MAX_VALUE))
        );

        getContentPane().add(sidebarPanel);
        sidebarPanel.setBounds(0, 0, 170, 490);

        contentPanel.setLayout(new java.awt.CardLayout());

        cardList.setBackground(new java.awt.Color(255, 255, 255));

        tableStudents.setBackground(new java.awt.Color(255, 255, 255));
        tableStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Course", "Email", "Date Created", "Date Updated"
            }
        ));
        tableStudents.setGridColor(new java.awt.Color(229, 231, 235));
        tableStudents.setOpaque(false);
        tableStudents.setRowHeight(28);
        tableStudents.setShowGrid(true);
        jScrollPane1.setViewportView(tableStudents);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("LIST OF ENROLLED STUDENTS");

        btnDeleteSelected.setBackground(new java.awt.Color(220, 38, 38));
        btnDeleteSelected.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteSelected.setText(" Delete Selected");
        btnDeleteSelected.addActionListener(this::btnDeleteSelectedActionPerformed);

        btnUpdateSelected.setBackground(new java.awt.Color(22, 163, 74));
        btnUpdateSelected.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdateSelected.setText("Update Selected");
        btnUpdateSelected.addActionListener(this::btnUpdateSelectedActionPerformed);

        btnReload.setBackground(new java.awt.Color(245, 158, 11));
        btnReload.setForeground(new java.awt.Color(0, 0, 0));
        btnReload.setText("Reload from Database");
        btnReload.addActionListener(this::btnReloadActionPerformed);

        btnSave.setBackground(new java.awt.Color(124, 58, 237));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save to Database");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout cardListLayout = new javax.swing.GroupLayout(cardList);
        cardList.setLayout(cardListLayout);
        cardListLayout.setHorizontalGroup(
            cardListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(18, 18, 18)
                .addComponent(btnReload)
                .addGap(182, 182, 182))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(cardListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(cardListLayout.createSequentialGroup()
                        .addComponent(btnDeleteSelected)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateSelected)))
                .addGap(22, 22, 22))
        );
        cardListLayout.setVerticalGroup(
            cardListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cardListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateSelected)
                    .addComponent(btnDeleteSelected))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(cardListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnReload))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        contentPanel.add(cardList, "card3");

        cardAdd.setBackground(new java.awt.Color(255, 255, 255));
        cardAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(17, 24, 39));
        jLabel1.setText("Add Student");

        tfName.setBackground(new java.awt.Color(255, 255, 255));
        tfName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfName.setText("jTextField1");
        tfName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(209, 213, 219), 1, true));
        tfName.addActionListener(this::tfNameActionPerformed);

        jlName.setForeground(new java.awt.Color(17, 24, 39));
        jlName.setText("Name:");

        jLabel5.setForeground(new java.awt.Color(17, 24, 39));
        jLabel5.setText("Course:");

        jcbCourse.setBackground(new java.awt.Color(255, 255, 255));
        jcbCourse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Bachelor of Technology Operations and Management  ", "Bachelor of Science in Architecture  ", "Bachelor of Science in Civil Engineering  ", "Bachelor of Science in Electronics Engineering  ", "Bachelor of Science in Electrical Engineering  ", "Bachelor of Science in Mechanical Engineering  ", "Bachelor of Science in Computer Engineering  ", "Bachelor of Science in Geodetic Engineering  ", "Bachelor of Science in Data Science  ", "Bachelor of Science in Computer Science  ", "Bachelor of Science in Information Technology  ", "Bachelor of Science in Technology Communication Management  ", "Bachelor of Science in Applied Physics  ", "Bachelor of Science in Applied Mathematics  ", "Bachelor of Science in Chemistry  ", "Bachelor of Science in Environmental Science  ", "Bachelor of Science in Food Technology  ", "Bachelor of Science in Autotronics  ", "Bachelor of Science in Electronics Technology  ", "Bachelor of Science in Energy Systems and Management  ", "Bachelor of Science in Electro-Mechanical Technology  ", "Bachelor of Science in Manufacturing Engineering Technology  ", "Bachelor of Science in Computer Science  ", "Bachelor of Secondary Education" }));

        jLabel6.setForeground(new java.awt.Color(17, 24, 39));
        jLabel6.setText("Email:");

        tfEmail.setBackground(new java.awt.Color(255, 255, 255));
        tfEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfEmail.setText("jTextField2");

        btnAdd.setBackground(new java.awt.Color(37, 99, 235));
        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnClear.setBackground(new java.awt.Color(229, 231, 235));
        btnClear.setForeground(new java.awt.Color(17, 24, 39));
        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        javax.swing.GroupLayout cardAddLayout = new javax.swing.GroupLayout(cardAdd);
        cardAdd.setLayout(cardAddLayout);
        cardAddLayout.setHorizontalGroup(
            cardAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardAddLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(cardAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardAddLayout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(44, 44, 44)
                        .addComponent(btnClear))
                    .addComponent(jlName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        cardAddLayout.setVerticalGroup(
            cardAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(cardAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnClear))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        contentPanel.add(cardAdd, "card2");

        getContentPane().add(contentPanel);
        contentPanel.setBounds(170, 60, 630, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
            
    }//GEN-LAST:event_formWindowClosing

    private void btnEnrolllingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrolllingActionPerformed
        restoreAddButton();
        clearFields();
        showCard("add");
    }//GEN-LAST:event_btnEnrolllingActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNameActionPerformed

    private void btnListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListActionPerformed
        restoreAddButton();
        refreshTableFromList();
        showCard("list");
        
    }//GEN-LAST:event_btnListActionPerformed

    private void btnDeleteSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSelectedActionPerformed
        deleteSelected();
    }//GEN-LAST:event_btnDeleteSelectedActionPerformed

    private void btnUpdateSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSelectedActionPerformed
        updateSelected();
    }//GEN-LAST:event_btnUpdateSelectedActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addStudent();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        reloadFromDB();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
            JOptionPane.showMessageDialog(this,
            "All data is automatically saved to the database.",
            "Info",
            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSaveActionPerformed
    private void addStudent() {
        String name   = tfName.getText().trim();
        String course = (String) jcbCourse.getSelectedItem();
        String email  = tfEmail.getText().trim();

        if (!validateInput(name, course, email)) return;

        LocalDateTime now = LocalDateTime.now();
        Student s = new Student(0, name, course.trim(), email, now, now);

        repository.insert(s);
        students.add(s);
        refreshTableFromList();
        clearFields();

        JOptionPane.showMessageDialog(this,
            "Student added successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteSelected() {
            int viewRow = tableStudents.getSelectedRow();
            System.out.println("Selected row: " + viewRow);
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a student row to delete.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the selected student?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        int modelRow = tableStudents.convertRowIndexToModel(viewRow);
        Student s = students.get(modelRow);
        System.out.println("Deleting student ID: " + s.getId());  

        repository.delete(s.getId());
        students.remove(modelRow);
        refreshTableFromList();

        JOptionPane.showMessageDialog(this,
            "Student deleted successfully.",
            "Deleted",
            JOptionPane.INFORMATION_MESSAGE);
}
    private void restoreAddButton() {
        for (java.awt.event.ActionListener al : btnAdd.getActionListeners()) {
            btnAdd.removeActionListener(al);
        }
        btnAdd.setText("Add");
        btnAdd.addActionListener(e -> addStudent());
    }

private void updateSelected() {
    int viewRow = tableStudents.getSelectedRow();
    if (viewRow < 0) {
        JOptionPane.showMessageDialog(this,
                "Please select a student row to update.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    int modelRow = tableStudents.convertRowIndexToModel(viewRow);
    Student s = students.get(modelRow);

    javax.swing.JTextField nameEdit   = new javax.swing.JTextField(s.getName());
    javax.swing.JTextField emailEdit  = new javax.swing.JTextField(s.getEmail());

    javax.swing.JComboBox<String> courseEdit = new javax.swing.JComboBox<>(jcbCourse.getModel());
    String targetCourse = s.getCourse().trim();
    for (int i = 0; i < courseEdit.getModel().getSize(); i++) {
        if (courseEdit.getModel().getElementAt(i).trim().equals(targetCourse)) {
            courseEdit.setSelectedIndex(i);
            break;
        }
    }

    javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(0, 1, 5, 5));
    panel.add(new javax.swing.JLabel("Name:"));
    panel.add(nameEdit);
    panel.add(new javax.swing.JLabel("Course:"));
    panel.add(courseEdit);
    panel.add(new javax.swing.JLabel("Email:"));
    panel.add(emailEdit);

    int result = JOptionPane.showConfirmDialog(
            this, panel, "Edit Student",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
        String newName   = nameEdit.getText().trim();
        String newCourse = (String) courseEdit.getSelectedItem();
        String newEmail  = emailEdit.getText().trim();

        if (!validateInput(newName, newCourse, newEmail)) return;

        s.setName(newName);
        s.setCourse(newCourse.trim());
        s.setEmail(newEmail);

        repository.update(s);

        Student updated = repository.findById(s.getId());
        if (updated != null) {
            s.setDateUpdated(updated.getDateUpdated());
        }

        refreshTableFromList();

        JOptionPane.showMessageDialog(this,
                "Student updated successfully.",
                "Updated",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
 
    private void loadFromDB() {
        try {
            List<Student> loaded = repository.loadAll();
            students.clear();
            students.addAll(loaded);
        } catch (Exception ex) {
            logger.warning("Could not load from DB: " + ex.getMessage());
        }

    }
    private void reloadFromDB() {
        loadFromDB();
        refreshTableFromList();
        setNextId();
        JOptionPane.showMessageDialog(this,
                "Data reloaded from database.",
                "Reloaded",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshTableFromList() {
        DefaultTableModel model = (DefaultTableModel) tableStudents.getModel();
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(new Object[]{
                s.getId(),
                s.getName(),
                s.getCourse(),
                s.getEmail(),
                s.getDateCreated() != null ? s.getDateCreated().format(FORMATTER) : "",
                s.getDateUpdated() != null ? s.getDateUpdated().format(FORMATTER) : ""
            });
        }
    }
    private void setNextId() {
                    nextId = students.stream()
                         .mapToInt(Student::getId)
                         .max()
                         .orElse(0) + 1;
    }
    
 
     private boolean validateInput(String name, String course, String email) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Student Name is required.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
 
        if (course != null) course = course.trim();
        if (course == null || course.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Course is required.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
 
        if (!email.isEmpty() && (!email.contains("@") || !email.contains("."))) {
            JOptionPane.showMessageDialog(this,
                    "Email must contain '@' and '.' (e.g. rheyheygwapo123@email.com).",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
     
    private void clearFields() {
        tfName.setText("");
         jcbCourse.setSelectedIndex(0);
        tfEmail.setText("");
        tfName.requestFocus();
    }
     
    private void showCard(String card) {
        java.awt.CardLayout cl = (java.awt.CardLayout) contentPanel.getLayout();
        if ("list".equals(card)) {
            cl.show(contentPanel, "card3");
        } else {
            cl.show(contentPanel, "card2");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeleteSelected;
    private javax.swing.JButton btnEnrollling;
    private javax.swing.JButton btnList;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdateSelected;
    private javax.swing.JPanel cardAdd;
    private javax.swing.JPanel cardList;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbCourse;
    private javax.swing.JLabel jlName;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JTable tableStudents;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
