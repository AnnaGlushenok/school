package school.model.interfaces;

import org.springframework.data.repository.CrudRepository;
import school.model.Test;

public interface TestRepository extends CrudRepository<Test, Integer> {
    Test findById(int integer);
}
