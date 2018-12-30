package tr.k12.ari.burhan.fokul.repositories;

import org.springframework.data.repository.CrudRepository;
import tr.k12.ari.burhan.fokul.model.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {
}