package ir.ac.alzahra.onlineexam.service.user;

import ir.ac.alzahra.onlineexam.model.data.User;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserService {

    Optional<User> findByEmail(String email);
}
