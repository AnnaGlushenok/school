package school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @GetMapping("/task")
    public String task(Model model) {
        return "task";
    }

    @GetMapping("/taskTeacher")
    public String taskTeacher() {

        return "taskTeacher";
    }


}
