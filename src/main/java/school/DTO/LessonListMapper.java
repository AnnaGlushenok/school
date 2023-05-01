package school.DTO;

import org.mapstruct.Mapper;
import school.model.Lesson;

import java.util.List;

@Mapper(componentModel = "spring", uses = LessonMapper.class)
public interface LessonListMapper {
    List<Lesson> toEntityList(List<LessonDTO> lessonDTOs);
    List<LessonDTO> toDTOList(List<Lesson> lessons);
}
