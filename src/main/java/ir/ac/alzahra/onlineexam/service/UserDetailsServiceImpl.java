package ir.ac.alzahra.onlineexam.service;

import ir.ac.alzahra.onlineexam.exception.UserNotFoundException;
import ir.ac.alzahra.onlineexam.model.data.User;
import ir.ac.alzahra.onlineexam.model.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final MessageSourceComponent messageSourceComponent;

    public UserDetailsServiceImpl(UserRepository userRepository, MessageSourceComponent messageSourceComponent) {
        this.userRepository = userRepository;
        this.messageSourceComponent = messageSourceComponent;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent())
            throw new UserNotFoundException(messageSourceComponent.getMessage("user.not.found"));
        else
            return new UserPrincipal(user.get());
    }
}
