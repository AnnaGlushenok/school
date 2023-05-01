package school.DTO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LessonsConverter implements Converter<String, List<LessonDTO>> {
    @Autowired
    @Qualifier("objectMapper")
    private final ObjectMapper objectMapper;

    public LessonsConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<LessonDTO> convert(String source) {
        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }

        try {
            List<LessonDTO> lessons = objectMapper.readValue(source, new TypeReference<List<LessonDTO>>() {
            });
            return lessons;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert lessons string to List<LessonDTO>.", e);
        }
    }
}

