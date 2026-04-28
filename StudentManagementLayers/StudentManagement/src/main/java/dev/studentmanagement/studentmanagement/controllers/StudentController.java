package dev.studentmanagement.studentmanagement.controllers;

import dev.studentmanagement.studentmanagement.entities.Student;
import dev.studentmanagement.studentmanagement.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
    return studentService
        .getStudentById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Student addStudent(@RequestBody Student student) {
    return studentService.addStudent(student);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeStudent(@PathVariable Long id) {
    boolean removed = studentService.removeStudent(id);
    if (!removed) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent(
      @PathVariable Long id, @RequestBody Student studentDetails) {
    return studentService
        .updateStudent(id, studentDetails)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
