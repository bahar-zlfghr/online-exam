package ir.ac.alzahra.onlineexam.service.token;

import ir.ac.alzahra.onlineexam.model.data.ConfirmationToken;
import ir.ac.alzahra.onlineexam.model.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public void save(ConfirmationToken confirmationTokenDto) {
        confirmationTokenRepository.save(confirmationTokenDto);
    }

    @Override
    public void update(ConfirmationToken confirmationToken, Boolean clickable) {
        confirmationToken.setClickable(clickable);
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public Optional<ConfirmationToken> findByTokenAndClickable(String token, Boolean clickable) {
        return confirmationTokenRepository.findByTokenAndClickable(token, clickable);
    }
}
