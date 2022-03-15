package ir.ac.alzahra.onlineexam.service.role;

import ir.ac.alzahra.onlineexam.model.data.Role;
import ir.ac.alzahra.onlineexam.model.repository.RoleRepository;
import ir.ac.alzahra.onlineexam.service.MessageSourceComponent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final MessageSourceComponent messageSourceComponent;

    public RoleServiceImpl(RoleRepository roleRepository, MessageSourceComponent messageSourceComponent) {
        this.roleRepository = roleRepository;
        this.messageSourceComponent = messageSourceComponent;
    }

    @PostConstruct
    private void init() {
        if (!roleRepository.findByName(messageSourceComponent.getMessage("role.admin")).isPresent())
            roleRepository.save(new Role().setName("admin").setDescription("admin role"));

        if (!roleRepository.findByName(messageSourceComponent.getMessage("role.teacher")).isPresent())
            roleRepository.save(new Role().setName("teacher").setDescription("teacher role"));

        if (!roleRepository.findByName(messageSourceComponent.getMessage("role.student")).isPresent())
            roleRepository.save(new Role().setName("student").setDescription("student role"));
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }
}
