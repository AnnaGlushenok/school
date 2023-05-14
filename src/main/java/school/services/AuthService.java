package school.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.model.Roles;
import school.model.Student;
import school.model.User;
import school.model.interfaces.ClassRepository;
import school.model.interfaces.StudentRepository;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

    public AuthService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public String getRole(User user) {
        Student student = studentRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
        return student == null ? null : student.getRole();
    }

    public boolean save(Student student) {
        if (!studentRepository.existsBySurnameAndNameAndPatronymic(student.getSurname(), student.getName(), student.getPatronymic())) {
            int id = classRepository.findByName(String.valueOf(student.getClassId())).get(0).getId();
            student.setClassId(id);
            student.setRole(Roles.STUDENT.name());
            studentRepository.save(student);
            return true;
        } else {
            return false;
        }
    }
}
