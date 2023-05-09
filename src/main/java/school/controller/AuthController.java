package school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import school.model.Roles;
import school.model.Student;
import school.model.interfaces.ClassRepository;
import school.model.interfaces.StudentRepository;

@Controller
public class AuthController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> registration(HttpServletResponse response, @RequestBody Student student) {
        if (!studentRepository.existsBySurnameAndNameAndPatronymic(student.getSurname(), student.getName(), student.getPatronymic())) {
            int id = classRepository.findByName(String.valueOf(student.getClassId())).get(0).getId();
            student.setClassId(id);
            student.setRole(Roles.STUDENT.name());
            studentRepository.save(student);
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseEntity.ok("Данные успешно обработаны");
        } else {
            return ResponseEntity.badRequest().body("Такой пользователь уже есть");
        }
    }

    //         sa@gmail.com
    public void auth() {

    }
}
