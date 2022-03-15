package ir.ac.alzahra.onlineexam.model.repository;

import ir.ac.alzahra.onlineexam.model.data.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
