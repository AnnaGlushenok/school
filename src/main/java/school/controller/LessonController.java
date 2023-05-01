package school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.DTO.ModuleDTO;
import school.model.Lesson;
import school.model.Module;
import school.model.Parser;
import school.model.interfaces.LessonRepository;
import school.model.interfaces.ModuleRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ModuleRepository moduleRepository;

//    @GetMapping("/lesson")
//    @ResponseBody
//    public List<Lesson> lesson(Model model) {
//        List<Lesson> l = lessonRepository.findAll();
//        return l;
//    }

    @GetMapping("/lessons")
    public String lessons(@RequestParam int subject_id, @RequestParam int class_id, Model model) {
        List<Module> modules = moduleRepository.findAllBySubjectIdAndClassId(subject_id, class_id);
        List<ModuleDTO> moduleDTOs = new ArrayList<>();
        for (Module module : modules)
       //     moduleDTOs.add(mapper.mapToModelDTO(module));
//        List<Lesson> lessons = new ArrayList<>();
//        for (Module module : modules) {
//            int[] id = Arrays.stream(module.getLessons().split(",")).mapToInt(Integer::parseInt).toArray();
//            for (int i : id)
//                lessons.add(lessonRepository.findById(i));
//        }
        model.addAttribute("modules", moduleDTOs);
        return "lessons";
    }

    @GetMapping("/lessonTeacher")
    public String teacherTask() {
        return "lesson_teacher";
    }

    @RequestMapping(value = "/lessonTeacher", method = RequestMethod.POST)
    public ResponseEntity<String> registration(HttpServletResponse response, @RequestBody Parser parser) {
        Lesson lesson = new Lesson();
        lesson.setTitle(parser.getTitle());
        lesson.setText(parser.convertToJson().toString());
        lessonRepository.save(lesson);
        response.setStatus(HttpServletResponse.SC_OK);
        return ResponseEntity.ok("Данные успешно обработаны");
    }
}
