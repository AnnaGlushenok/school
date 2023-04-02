package school.model.interfaces;

import org.springframework.data.repository.CrudRepository;
import school.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
