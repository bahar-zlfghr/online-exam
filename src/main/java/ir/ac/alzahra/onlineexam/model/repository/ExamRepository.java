package ir.ac.alzahra.onlineexam.model.repository;

import ir.ac.alzahra.onlineexam.model.data.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Set<Exam> findByCourseId(Long courseId);

    @Modifying
    @Query("DELETE FROM Exam AS e WHERE e.id = :id")
    void deleteExam(@Param("id") Long id);

}
