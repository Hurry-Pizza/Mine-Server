package com.hurrypizza.digda.domain.user;

import com.hurrypizza.digda.exception.LoginFailedException;
import com.hurrypizza.digda.exception.ResourceAlreadyExistException;
import com.hurrypizza.digda.exception.ResourceNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public User validateUserByEmailAndPassword(final String email, final String password) {
        var user = findByEmail(email);
        if (!encoder.matches(password, user.getPassword())) {
            throw new LoginFailedException("Password is wrong");
        }
        return user;
    }

    @Transactional
    @Override
    public void save(String email, String password, String color) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResourceAlreadyExistException("Email: %s".formatted(email));
        }
        userRepository.save(User.createNormalUser(email, password, color));
    }

    private User findByEmail(final String email) {
        return userRepository.findByEmail(email)
                       .orElseThrow(ResourceNotExistException::new);
    }

}
