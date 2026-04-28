import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManagmentUI extends JFrame implements ActionListener {
  private Object[][] students;
  private JTable table;
  private DefaultTableModel dtm;
  private JButton addBtn, remBtn, updBtn, prtBtn, exitBtn;

  public StudentManagmentUI() {
    students = new Object[0][];
    setTitle("Student Manager");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    dtm = new DefaultTableModel(new String[] {"ID", "Name", "Last Name", "Age"}, 0);
    table = new JTable(dtm);
    JScrollPane sp = new JScrollPane(table);
    add(sp, BorderLayout.CENTER);

    JPanel p = new JPanel();
    addBtn = new JButton("Add Student");
    remBtn = new JButton("Remove Student");
    updBtn = new JButton("Update Student");
    prtBtn = new JButton("Print Students");
    exitBtn = new JButton("Exit");

    addBtn.addActionListener(this);
    remBtn.addActionListener(this);
    updBtn.addActionListener(this);
    prtBtn.addActionListener(this);
    exitBtn.addActionListener(this);

    p.add(addBtn);
    p.add(remBtn);
    p.add(updBtn);
    p.add(prtBtn);
    p.add(exitBtn);
    add(p, BorderLayout.NORTH);

    pack();
    setSize(600, 400);
    setLocationRelativeTo(null);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addBtn) {
      String n = JOptionPane.showInputDialog("Enter Name:");
      if (n == null) return;
      String l = JOptionPane.showInputDialog("Enter Last Name:");
      if (l == null) return;
      String aStr = JOptionPane.showInputDialog("Enter Age:");
      if (aStr == null) return;
      int a = Integer.parseInt(aStr);
      Object[] newStudent = {n, l, a};
      Object[][] newArr = new Object[students.length + 1][];
      System.arraycopy(students, 0, newArr, 0, students.length);
      newArr[students.length] = newStudent;
      students = newArr;
      dtm.setRowCount(0);
      for (int i = 0; i < students.length; i++) {
        dtm.addRow(new Object[] {i, students[i][0], students[i][1], students[i][2]});
      }
    } else if (e.getSource() == remBtn) {
      if (students.length == 0) {
        JOptionPane.showMessageDialog(this, "No students");
        return;
      }
      String idStr = JOptionPane.showInputDialog("Enter Student ID to remove:");
      if (idStr == null) return;
      int id = Integer.parseInt(idStr);
      if (id < 0 || id >= students.length) {
        JOptionPane.showMessageDialog(this, "Invalid ID");
        return;
      }
      Object[][] newArr = new Object[students.length - 1][];
      System.arraycopy(students, 0, newArr, 0, id);
      System.arraycopy(students, id + 1, newArr, id, students.length - id - 1);
      students = newArr;
      dtm.setRowCount(0);
      for (int i = 0; i < students.length; i++) {
        dtm.addRow(new Object[] {i, students[i][0], students[i][1], students[i][2]});
      }
    } else if (e.getSource() == updBtn) {
      if (students.length == 0) {
        JOptionPane.showMessageDialog(this, "No students");
        return;
      }
      String idStr = JOptionPane.showInputDialog("Enter Student ID to update:");
      if (idStr == null) return;
      int id = Integer.parseInt(idStr);
      if (id < 0 || id >= students.length) {
        JOptionPane.showMessageDialog(this, "Invalid ID");
        return;
      }
      String n = JOptionPane.showInputDialog("Enter new Name:");
      if (n == null) return;
      String l = JOptionPane.showInputDialog("Enter new Last Name:");
      if (l == null) return;
      String aStr = JOptionPane.showInputDialog("Enter new Age:");
      if (aStr == null) return;
      int a = Integer.parseInt(aStr);
      students[id][0] = n;
      students[id][1] = l;
      students[id][2] = a;
      dtm.setRowCount(0);
      for (int i = 0; i < students.length; i++) {
        dtm.addRow(new Object[] {i, students[i][0], students[i][1], students[i][2]});
      }
    } else if (e.getSource() == prtBtn) {
      if (students.length == 0) {
        JOptionPane.showMessageDialog(this, "No students");
        return;
      }
      StringBuilder sb = new StringBuilder("List of Students:\n");
      for (int i = 0; i < students.length; i++) {
        sb.append(i)
            .append(" Name: ")
            .append(students[i][0])
            .append(" Last Name: ")
            .append(students[i][1])
            .append(" Age: ")
            .append(students[i][2])
            .append("\n");
      }
      JOptionPane.showMessageDialog(this, sb.toString());
    } else if (e.getSource() == exitBtn) {
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new StudentManagmentUI().setVisible(true));
  }
}
