package school.controller;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import school.model.*;
import school.model.interfaces.ClassRepository;
import school.model.interfaces.StudentRepository;
import school.services.AuthService;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

//    @Autowired
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }

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

    @GetMapping(value = "/auth")
    public ModelAndView auth(Model model) {
        return new ModelAndView("auth");
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Student authRequest) {
        String s = authRequest.getPassword();
        String ss = authRequest.getLogin();
        return "ResponseEntity.ok()";
    }

    @RequestMapping(value = "/auth2", method = RequestMethod.POST)
    @ResponseBody
    public String login2(@RequestBody Student authRequest) {
        String s = authRequest.getPassword();
        String ss = authRequest.getLogin();
        return "ResponseEntity.ok()";
    }

//    @PostMapping("/access_token")
//    public ResponseEntity<JWTResponse> getNewAccessToken(@RequestBody RefreshJWTRequest request) {
//        final JWTResponse token = authService.getAccessToken(request.getRefreshToken());
//        return ResponseEntity.ok(token);
//    }
//
//    @PostMapping("/refresh_token")
//    public ResponseEntity<JWTResponse> getNewRefreshToken(@RequestBody RefreshJWTRequest request) {
//        final JWTResponse token;
//        try {
//            token = authService.refresh(request.getRefreshToken());
//        } catch (AuthException e) {
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(token);
//    }
}
