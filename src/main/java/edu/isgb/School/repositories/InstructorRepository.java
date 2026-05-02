package edu.isgb.School.repositories;



import edu.isgb.School.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    List<Instructor> findByName(String name);
}
