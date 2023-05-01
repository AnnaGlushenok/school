package school.DTO;

import lombok.*;
import school.model.Lesson;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ModuleDTO {
    private int id;
    private int subjectId;
    private int classId;
    private int testId;
    private String name;
    private List<LessonDTO> lessons;
//    private List<Control> сontrol;
//    private List<Task> tasks;
}
