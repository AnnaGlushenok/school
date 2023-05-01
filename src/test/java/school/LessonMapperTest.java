package school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import school.DTO.LessonDTO;
import school.DTO.LessonListMapper;
import school.DTO.LessonMapper;
import school.model.Lesson;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LessonMapperTest {
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private LessonListMapper lessonListMapper;


    @Test
    void mapToDTO() {
        Lesson lesson = new Lesson(10, 2, "тест", "большой текст урока");
        LessonDTO lessonDTO = lessonMapper.toDTO(lesson);

        Assertions.assertNotNull(lessonDTO);
        Assertions.assertEquals(lesson.getId(), lessonDTO.getId());
        Assertions.assertEquals(lesson.getText(), lessonDTO.getText());
        Assertions.assertEquals(lesson.getTestId(), lessonDTO.getTestId());
    }

    @Test
    void mapToEntity() {
        LessonDTO lessonDTO = new LessonDTO(10, 2, "тест", "большой текст урока");
        Lesson lesson = lessonMapper.toEntity(lessonDTO);

        Assertions.assertNotNull(lessonDTO);
        Assertions.assertEquals(lesson.getId(), lessonDTO.getId());
        Assertions.assertEquals(lesson.getText(), lessonDTO.getText());
        Assertions.assertEquals(lesson.getTestId(), lessonDTO.getTestId());
    }

    @Test
    void mapToListDTO() {
        List<Lesson> lessonList = new ArrayList<Lesson>() {
            {
                add(new Lesson(8, 4, "тест", "большой текст урока"));
                add(new Lesson(10, 2, "тест общий", "большой второй текст урока"));
            }
        };

        List<LessonDTO> lessonDTOs = lessonListMapper.toDTOList(lessonList);

        Assertions.assertNotNull(lessonDTOs);
        Assertions.assertEquals(lessonList.size(), lessonDTOs.size());
        for (int i = 0; i < lessonList.size(); i++) {
            Assertions.assertEquals(lessonList.get(i).getId(), lessonDTOs.get(i).getId());
            Assertions.assertEquals(lessonList.get(i).getText(), lessonDTOs.get(i).getText());
            Assertions.assertEquals(lessonList.get(i).getTestId(), lessonDTOs.get(i).getTestId());
        }
    }

    @Test
    void mapToListEntity() {
        List<LessonDTO> lessonDTOList = new ArrayList<LessonDTO>() {
            {
                add(new LessonDTO(8, 4, "тест", "большой текст урока"));
                add(new LessonDTO(10, 2, "тест общий", "большой второй текст урока"));
            }
        };

        List<Lesson> lessons = lessonListMapper.toEntityList(lessonDTOList);

        Assertions.assertNotNull(lessons);
        Assertions.assertEquals(lessonDTOList.size(), lessons.size());
        for (int i = 0; i < lessonDTOList.size(); i++) {
            Assertions.assertEquals(lessonDTOList.get(i).getId(), lessons.get(i).getId());
            Assertions.assertEquals(lessonDTOList.get(i).getText(), lessons.get(i).getText());
            Assertions.assertEquals(lessonDTOList.get(i).getTestId(), lessons.get(i).getTestId());
        }
    }
}
