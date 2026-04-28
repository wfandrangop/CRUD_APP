package dev.studentmanagement.studentmanagement.services;

import dev.studentmanagement.studentmanagement.entities.Student;
import dev.studentmanagement.studentmanagement.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Optional<Student> getStudentById(Long id) {
    return studentRepository.findById(id);
  }

  public Student addStudent(Student student) {
    return studentRepository.save(student);
  }

  public boolean removeStudent(Long id) {
    if (!studentRepository.existsById(id)) {
      return false;
    }
    studentRepository.deleteById(id);
    return true;
  }

  public Optional<Student> updateStudent(Long id, Student studentDetails) {
    return studentRepository
        .findById(id)
        .map(
            student -> {
              student.setName(studentDetails.getName());
              student.setLastName(studentDetails.getLastName());
              student.setAge(studentDetails.getAge());
              return studentRepository.save(student);
            });
  }
}
