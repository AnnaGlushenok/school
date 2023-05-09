package school.model.interfaces;

import org.springframework.data.repository.CrudRepository;
import school.model.Lesson;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    Lesson findById(int id);

    List<Lesson> findAll();
}
