package school.DTO;

import org.mapstruct.Mapper;
import school.model.Lesson;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDTO toDTO(Lesson lesson);

    Lesson toEntity(LessonDTO lessonDTO);
}
