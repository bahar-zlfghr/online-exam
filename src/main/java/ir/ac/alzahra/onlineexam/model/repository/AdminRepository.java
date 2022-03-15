package ir.ac.alzahra.onlineexam.model.repository;

import ir.ac.alzahra.onlineexam.model.data.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findById(Long id);
}
