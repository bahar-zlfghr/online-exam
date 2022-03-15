package ir.ac.alzahra.onlineexam.model.repository;

import ir.ac.alzahra.onlineexam.model.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Bahar Zolfaghari
 **/
public interface StudentRepository extends JpaRepository<Student, Long> {


}
