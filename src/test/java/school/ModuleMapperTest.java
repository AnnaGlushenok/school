package school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import school.DTO.ModuleDTO;
import school.DTO.ModuleMapper;
import school.model.Lesson;
import school.model.Module;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ModuleMapperTest {
    @Autowired
    private ModuleMapper moduleMapper;

    @Test
    void mapToDTO() {
        Module module = new Module(4, 4, 2, 4, "имя");
        List<Lesson> lessonList = new ArrayList<Lesson>() {
            {
                add(new Lesson(8, 4, "тест", "большой текст урока"));
                add(new Lesson(10, 2, "тест общий", "большой второй текст урока"));
            }
        };

        ModuleDTO moduleDTO = moduleMapper.toDTO(module);

        Assertions.assertNotNull(moduleDTO);
        Assertions.assertEquals(module.getId(), moduleDTO.getId());
        Assertions.assertEquals(module.getName(), moduleDTO.getName());
        for (int i = 0; i < lessonList.size(); i++) {
            Assertions.assertEquals(lessonList.get(i).getId(), moduleDTO.getLessons().get(i).getId());
            Assertions.assertEquals(lessonList.get(i).getText(), moduleDTO.getLessons().get(i).getText());
            Assertions.assertEquals(lessonList.get(i).getTestId(), moduleDTO.getLessons().get(i).getTestId());
        }
    }

    @Test
    void mapTaoDTO() {
        Module module = new Module(4, 4, 2, 4, "имя");
        List<Lesson> lessonList = new ArrayList<Lesson>() {
            {
                add(new Lesson(8, 4, "тест", "большой текст урока"));
                add(new Lesson(10, 2, "тест общий", "большой второй текст урока"));
            }
        };

        ModuleDTO moduleDTO = moduleMapper.toDTO(module);

        Assertions.assertNotNull(moduleDTO);
        Assertions.assertEquals(module.getId(), moduleDTO.getId());
        Assertions.assertEquals(module.getName(), moduleDTO.getName());
        for (int i = 0; i < lessonList.size(); i++) {
            Assertions.assertEquals(lessonList.get(i).getId(), moduleDTO.getLessons().get(i).getId());
            Assertions.assertEquals(lessonList.get(i).getText(), moduleDTO.getLessons().get(i).getText());
            Assertions.assertEquals(lessonList.get(i).getTestId(), moduleDTO.getLessons().get(i).getTestId());
        }
    }
}
