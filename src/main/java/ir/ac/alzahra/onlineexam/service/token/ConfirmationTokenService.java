package ir.ac.alzahra.onlineexam.service.token;

import ir.ac.alzahra.onlineexam.model.data.ConfirmationToken;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ConfirmationTokenService {

    void save(ConfirmationToken confirmationToken);
    void update(ConfirmationToken confirmationToken, Boolean clickable);
    Optional<ConfirmationToken> findByTokenAndClickable(String token, Boolean clickable);
}
