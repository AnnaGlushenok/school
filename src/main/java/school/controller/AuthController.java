package school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import school.model.*;
import school.services.AuthService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> registration(HttpServletResponse response, @RequestBody Student student) {
        if (authService.save(student)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseEntity.ok("Данные успешно обработаны");
        } else {
            return ResponseEntity.badRequest().body("Такой пользователь уже есть");
        }
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @GetMapping(value = "/")
    public ModelAndView auth(Model model) {
        return new ModelAndView("auth");
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(@RequestBody User user) {
        String role = authService.getRole(user);
        Map<String, String> map = new HashMap<>();
        map.put("role", role);
        return map;
    }
}
