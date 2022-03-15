package ir.ac.alzahra.onlineexam.model.repository;

import ir.ac.alzahra.onlineexam.model.data.EducationalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface EducationalUserRepository extends JpaRepository<EducationalUser, Long>, JpaSpecificationExecutor<EducationalUser> {

    @Modifying
    @Query("UPDATE EducationalUser AS eu SET eu.emailVerification = :emailVerification WHERE eu.id = :id")
    void updateEmailVerification(@Param("id") Long id, @Param("emailVerification") Boolean emailVerification);

    @Modifying
    @Query("UPDATE EducationalUser AS eu SET eu.adminVerification = :adminVerification WHERE eu.id = :id")
    void updateAdminVerification(@Param("id") Long id, @Param("adminVerification") Boolean adminVerification);

    Optional<EducationalUser> findById(Long id);
}
