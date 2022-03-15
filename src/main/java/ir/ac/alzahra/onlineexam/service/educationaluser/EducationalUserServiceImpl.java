package ir.ac.alzahra.onlineexam.service.educationaluser;

import ir.ac.alzahra.onlineexam.model.data.EducationalUser;
import ir.ac.alzahra.onlineexam.model.repository.EducationalUserRepository;
import ir.ac.alzahra.onlineexam.model.repository.EducationalUserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class EducationalUserServiceImpl implements EducationalUserService {
    private final EducationalUserRepository educationalUserRepository;

    public EducationalUserServiceImpl(EducationalUserRepository educationalUserRepository) {
        this.educationalUserRepository = educationalUserRepository;
    }

    @Override
    public void updateEmailVerification(Long id, Boolean emailVerification) {
        educationalUserRepository.updateEmailVerification(id, emailVerification);
    }

    @Override
    public void updateAdminVerification(Long id, Boolean adminVerification) {
        educationalUserRepository.updateAdminVerification(id, adminVerification);
    }

    @Override
    public void updateAdminCourses(EducationalUser educationalUser) {
        educationalUserRepository.saveAndFlush(educationalUser);
        educationalUserRepository.flush();
    }

    @Override
    public EducationalUser findById(Long id) {
        return educationalUserRepository.findById(id).orElse(null);
    }

    @Override
    public Set<EducationalUser> findAll() {
        return new HashSet<>(educationalUserRepository.findAll());
    }

    @Override
    public Set<EducationalUser> findAll(String firstName, String lastName, String email, String role) {
        return new HashSet<>(educationalUserRepository.findAll(Specification
                .where(EducationalUserSpecification.search(firstName, lastName, email, role))));
    }
}
