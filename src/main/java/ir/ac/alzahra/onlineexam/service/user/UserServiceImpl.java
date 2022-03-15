package ir.ac.alzahra.onlineexam.service.user;

import ir.ac.alzahra.onlineexam.model.data.Admin;
import ir.ac.alzahra.onlineexam.model.data.Role;
import ir.ac.alzahra.onlineexam.model.data.User;
import ir.ac.alzahra.onlineexam.model.repository.RoleRepository;
import ir.ac.alzahra.onlineexam.model.repository.UserRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@DependsOn({"roleServiceImpl"})
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {
        if (!userRepository.findByEmail("admin@gmail.com").isPresent()) {
            Set<Role> roles = new HashSet<>(roleRepository.findAll());
            userRepository.save(new Admin()
                    .setFirstName("bahar")
                    .setLastName("zolfaghari")
                    .setEmail("admin@gmail.com")
                    .setPassword(passwordEncoder.encode("1234"))
                    .setRoles(roles)
                    .setEnabled(true));
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
