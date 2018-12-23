package tr.k12.ari.burhan.fokul.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tr.k12.ari.burhan.fokul.model.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Iterable<Grade> findByName(String name);

    @Query("select c from Grade c where c.name = :name order by c.name")
    Iterable<Grade> findByNameUsingJPQL(@Param("name") String name);
}