import java.util.Scanner;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Main {
  public static Object[][] students;

  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    students = new Object[0][];
    boolean option = true;
    do {
      System.out.println("=============================");
      System.out.println("1. Add Student");
      System.out.println("2. Remove Student");
      System.out.println("3. Print Students");
      System.out.println("4. Update Student");
      System.out.println("5. Exit");
      System.out.println("=============================");
      System.out.println("Option: ");
      int optionNumber = reader.nextInt();
      switch (optionNumber) {
        case 1:
          System.out.println("=============================");
          System.out.println("Enter Student Name: ");
          String studentName = reader.next();
          System.out.println("Enter Student Last Name: ");
          String studentLastName = reader.next();
          System.out.println("Enter Student Age: ");
          int studentAge = reader.nextInt();
          addStudent(students, studentName, studentLastName, studentAge);
          break;
        case 2:
          System.out.println("=============================");
          System.out.println("Remove Student:");
          System.out.println("Enter Student ID: ");
          int removeStudentId = reader.nextInt();
          removeStudent(students, removeStudentId);
          break;
        case 3:
          System.out.println("=============================");
          System.out.println("List of Students: ");
          printStudents(students);
          break;
        case 4:
          System.out.println("=============================");
          System.out.println("Update Student:");
          System.out.println("Enter Student ID: ");
          int updateStudentId = reader.nextInt();
          System.out.println("=============================");
          System.out.println("Enter Student Name: ");
          String studentNameUpdate = reader.next();
          System.out.println("Enter Student Last Name: ");
          String studentLastNameUpdate = reader.next();
          System.out.println("Enter Student Age: ");
          int studentAgeUpdate = reader.nextInt();
          updateStudent(
              students,
              updateStudentId,
              studentNameUpdate,
              studentLastNameUpdate,
              studentAgeUpdate);
          break;
        case 5:
          System.out.println("=============================");
          System.out.println("Exiting...");
          option = false;
          break;
        default:
          System.out.println("Invalid Option");
          break;
      }
    } while (option);
  }

  public static void printStudents(Object[][] students) {
    int counter = 0;
    for (Object[] student : students) {
      System.out.println("=============================");
      if (student != null) {
        System.out.println(
            counter + " Name: " + student[0] + " Last Name:" + student[1] + " Age:" + student[2]);
      }
      counter++;
    }
  }

  public static void updateStudent(
      Object[][] students, int index, String newName, String lastName, int newAge) {
    if (index < 0 || index >= students.length) {
      System.out.println("Index out of bounds");
      return;
    }

    students[index][0] = newName;
    students[index][1] = lastName;
    students[index][2] = newAge;
  }

  public static void removeStudent(Object[][] currentStudents, int index) {

    if (currentStudents == null || currentStudents.length == 0) {
      System.out.println("There are no students to remove");
      return;
    }
    if (index < 0 || index >= currentStudents.length) {
      System.out.println("Index out of bounds");
      return;
    }
    Object[][] newArray = new Object[currentStudents.length - 1][];
    System.arraycopy(currentStudents, 0, newArray, 0, index);
    System.arraycopy(
        currentStudents, index + 1, newArray, index, currentStudents.length - index - 1);
    Main.students = newArray;
    System.out.println("Student Removed");
  }

  public static void addStudent(Object[][] currentStudents, String name, String lastname, int age) {
    Object[] newStudent = {name, lastname, age};
    Object[][] newArray = new Object[currentStudents.length + 1][];
    System.arraycopy(currentStudents, 0, newArray, 0, currentStudents.length);
    newArray[currentStudents.length] = newStudent;
    Main.students = newArray;
    System.out.println("Student Added");
  }
}

