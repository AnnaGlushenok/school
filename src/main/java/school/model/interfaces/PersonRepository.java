package school.model.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import school.model.Person;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByLastName(@Param("name") String name);

    List<Person> findAll();
}