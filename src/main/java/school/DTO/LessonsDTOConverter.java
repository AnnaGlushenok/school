package school.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonsDTOConverter implements Converter<List<LessonDTO>, String> {
    @Autowired
    @Qualifier("objectMapper")
    private final ObjectMapper objectMapper;

    public LessonsDTOConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convert(List<LessonDTO> value) {
        return String.join(",", (String[]) value.stream().map(v -> String.valueOf(v.getId())).toArray());
    }
}
