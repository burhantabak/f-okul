package tr.k12.ari.burhan.fokul.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tr.k12.ari.burhan.fokul.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Iterable<Student> findByName(String name);

    @Query("select c from Student c where c.name = :name order by c.name")
    Iterable<Student> findByNameUsingJPQL(@Param("name") String name);
}