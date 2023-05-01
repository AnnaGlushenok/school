package school.DTO;

import lombok.Data;

@Data
public class LessonDTO {
    private int id;
    private int testId;
    private String title;
    private String text;

    public LessonDTO() {
    }

    public LessonDTO(int id, int testId, String title, String text) {
        this.id = id;
        this.testId = testId;
        this.title = title;
        this.text = text;
    }
}
