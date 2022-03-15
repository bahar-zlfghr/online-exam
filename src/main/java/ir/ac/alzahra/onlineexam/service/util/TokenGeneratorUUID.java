package ir.ac.alzahra.onlineexam.service.util;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class TokenGeneratorUUID implements TokenGenerator {

	@Override
	public String generateRandomToken() {
		return UUID.randomUUID().toString();
	}
}
