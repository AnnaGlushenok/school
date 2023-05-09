package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.model.interfaces.ClassRepository;
import school.model.interfaces.SubjectRepository;

@Controller
public class MainController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ClassRepository classRepository;
    private int subject_id, class_id;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("classes", classRepository.findAll());
        return "index";
    }

    @GetMapping("/menu")
    public String menu(@RequestParam String subject_id, @RequestParam String class_id, Model model) {
        this.subject_id = Integer.parseInt(subject_id);
        this.class_id = Integer.parseInt(class_id);
        model.addAttribute("subject_id", this.subject_id);
        model.addAttribute("class_id", this.class_id);
        return "menu";
    }

    @GetMapping("/practical")
    public String practical(Model model) {
        return "practical";
    }

    @GetMapping("/control")
    public String control(Model model) {
        return "control";
    }

    @GetMapping("/auth")
    public String auth(Model model) {
        return "auth";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }
}
