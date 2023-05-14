package school.model.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("select (count(s) > 0) from students s where s.surname = ?1 and s.name = ?2 and s.patronymic = ?3")
    boolean existsBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

    List<Student> findAll();

    Student findByLoginAndPassword(String login, String password);
}