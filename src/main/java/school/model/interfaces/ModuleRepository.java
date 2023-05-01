package school.model.interfaces;

import org.springframework.data.repository.CrudRepository;
import school.model.Module;

import java.util.List;

public interface ModuleRepository extends CrudRepository<Module, Integer> {
    List<Module> findAllBySubjectIdAndClassId(int subjectId, int classId);
}
