package ir.ac.alzahra.onlineexam.service.role;

import ir.ac.alzahra.onlineexam.model.data.Role;

import java.util.Optional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface RoleService {

    Set<Role> findAll();
    Optional<Role> findById(Integer id);
}
