package ir.ac.alzahra.onlineexam.model.repository;

import ir.ac.alzahra.onlineexam.model.data.EducationalUser;
import ir.ac.alzahra.onlineexam.model.data.Role;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : Bahar Zolfaghari
 **/
public interface EducationalUserSpecification {

    static Specification<EducationalUser> search(String firstName, String lastName, String email, String role) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            CriteriaQuery<EducationalUser> educationalUserCriteriaQuery = criteriaBuilder.createQuery(EducationalUser.class);

            Join<EducationalUser, Role> roles = root.join("roles");


            if (!Objects.isNull(firstName) && !firstName.isEmpty())
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));

            if (!Objects.isNull(lastName) && !lastName.isEmpty())
                predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));

            if (!Objects.isNull(email) && !email.isEmpty())
                predicates.add(criteriaBuilder.equal(root.get("email"), email));

            if (!Objects.isNull(role) && !role.isEmpty())
                predicates.add(criteriaBuilder.equal(roles.get("name"), role));

            return educationalUserCriteriaQuery.select(root).where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
