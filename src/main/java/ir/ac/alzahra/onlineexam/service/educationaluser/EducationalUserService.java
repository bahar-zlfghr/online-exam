package ir.ac.alzahra.onlineexam.service.educationaluser;

import ir.ac.alzahra.onlineexam.model.data.EducationalUser;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface EducationalUserService {

    void updateEmailVerification(Long id, Boolean emailVerification);
    void updateAdminVerification(Long id, Boolean adminVerification);
    void updateAdminCourses(EducationalUser educationalUser);
    EducationalUser findById(Long id);
    Set<EducationalUser> findAll();
    Set<EducationalUser> findAll(String firstName, String lastName, String email, String role);
}
