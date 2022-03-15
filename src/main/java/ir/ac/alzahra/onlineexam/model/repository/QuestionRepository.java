package ir.ac.alzahra.onlineexam.model.repository;

import ir.ac.alzahra.onlineexam.model.data.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
