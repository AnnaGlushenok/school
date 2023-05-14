package school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.model.Question;
import school.model.Test;
import school.model.TestAnswer;
import school.model.interfaces.TestRepository;

import java.time.format.SignStyle;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Controller
public class TestController {
    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/test/{id}/{time}")
    @ResponseBody
    public Map<String, String> test1(@PathVariable("id") int id, @PathVariable("time") long time, @RequestBody List<TestAnswer> testAnswer) {
        long end = System.currentTimeMillis();
        String tests = testRepository.findById(id).getTest();
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(tests, Question[].class);
        double mark = 0;
        for (int i = 0; i < questions.length; i++) {
            String[] answer = questions[i].getAnswer().split(",");
            List<String> answers = testAnswer.get(i).getAns();
            double score = 1.0 / answer.length;
            if (answer.length > 1) {
                for (String s : answer)
                    mark += answers.contains(s) ? score : 0;
            } else {
                mark += answers.get(0).equals(answer[i]) ? 1 : 0;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("mark", String.valueOf(mark));
        map.put("time", String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(end - time), TimeUnit.MILLISECONDS.toSeconds(end - time)));
        return map;
    }

    @GetMapping("/getTest")
    @ResponseBody
    public Test getTest() {
        return testRepository.findById(103);
    }

    @GetMapping("/testTeacher")
    public String testTeacher() {
        return "test_teacher";
    }

    @RequestMapping(value = "/testTeacher", method = RequestMethod.POST)
    public ResponseEntity<String> addTest(@RequestBody List<Question> questions) {
        StringBuilder str = new StringBuilder("[");
        for (Question question : questions)
            str.append(question.toString()).append(",");
        str.delete(str.length() - 1, str.length());
        str.append("]");
        Test test = new Test("", str.toString());
        testRepository.save(test);
        return ResponseEntity.ok("Данные успешно обработаны");
    }
}
