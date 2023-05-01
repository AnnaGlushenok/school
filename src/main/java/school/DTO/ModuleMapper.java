package school.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Qualifier;
import school.model.Module;

import java.util.List;

//@Mapper(componentModel = "spring", uses = {LessonListMapper.class,S.class})
@Mapper(componentModel = "spring", uses = {LessonsDTOConverter.class})
public interface ModuleMapper {
    @Mapping(source = "lessons", target = "lessons", qualifiedByName = "listToLessons")
    Module toEntity(ModuleDTO moduleDTO);

    @Mapping(source = "lessons", target = "lessons", qualifiedByName = "lessonsToList")
    ModuleDTO toDTO(Module module);

    @Named("lessonsToList")
    default List<LessonDTO> lessonsToList(String lessons, LessonsConverter lessonsConverter) {
        return lessonsConverter.convert(lessons);
    }

    @Named("listToLessons")
    default String lessonsToList(List<LessonDTO> lessons, LessonsDTOConverter lessonsConverter) {
        return lessonsConverter.convert(lessons);
    }
}
