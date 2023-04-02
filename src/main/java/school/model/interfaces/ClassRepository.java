package school.model.interfaces;

import org.springframework.data.repository.CrudRepository;
import school.model.Class;

public interface ClassRepository extends CrudRepository<Class, Integer> {
}
