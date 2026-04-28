package dev.studentmanagement.studentmanagement.controllers;

import dev.studentmanagement.studentmanagement.entities.Student;
import dev.studentmanagement.studentmanagement.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StudentViewController {

  private final StudentService studentService;

  public StudentViewController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/")
  public String home() {
    return "Home";
  }

  @GetMapping("/students")
  public String listStudents(
      @RequestParam(required = false) String message,
      @RequestParam(required = false) String error,
      Model model) {
    model.addAttribute("students", studentService.getAllStudents());
    model.addAttribute("message", message);
    model.addAttribute("error", error);
    return "ListStudents";
  }

  @GetMapping("/students/new")
  public String showAddStudentForm(Model model) {
    model.addAttribute("student", new Student());
    return "AddStudent";
  }

  @PostMapping("/students")
  public String addStudent(@ModelAttribute Student student) {
    studentService.addStudent(student);
    return "redirect:/students?message=Estudiante+creado+correctamente";
  }

  @GetMapping("/students/edit")
  public String goToEditForm(@RequestParam Long id) {
    return "redirect:/students/" + id + "/edit";
  }

  @GetMapping("/students/{id}/edit")
  public String showUpdateStudentForm(@PathVariable Long id, Model model) {
    Optional<Student> student = studentService.getStudentById(id);
    if (student.isEmpty()) {
      return "redirect:/students?error=Estudiante+no+encontrado";
    }
    model.addAttribute("student", student.get());
    return "UpdateStudents";
  }

  @PostMapping("/students/{id}/edit")
  public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
    Optional<Student> updatedStudent = studentService.updateStudent(id, student);
    if (updatedStudent.isEmpty()) {
      return "redirect:/students?error=Estudiante+no+encontrado";
    }
    return "redirect:/students?message=Estudiante+actualizado+correctamente";
  }

  @PostMapping("/students/{id}/delete")
  public String removeStudent(@PathVariable Long id) {
    boolean removed = studentService.removeStudent(id);
    if (!removed) {
      return "redirect:/students?error=Estudiante+no+encontrado";
    }
    return "redirect:/students?message=Estudiante+eliminado+correctamente";
  }
}
