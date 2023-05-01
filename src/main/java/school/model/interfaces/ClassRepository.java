package school.model.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import school.model.Class;

import java.util.List;

public interface ClassRepository extends CrudRepository<Class, Integer> {
    List<Class> findByName(@Param("name") String name);
}
