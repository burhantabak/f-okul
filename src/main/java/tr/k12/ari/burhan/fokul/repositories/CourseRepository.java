package tr.k12.ari.burhan.fokul.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tr.k12.ari.burhan.fokul.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Iterable<Course> findByName(String name);

    @Query("select c from Course c where c.name = :name order by c.name")
    Iterable<Course> findByNameUsingJPQL(@Param("name") String name);
}