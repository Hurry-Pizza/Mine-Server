package com.hurrypizza.mine.domain.user;

import com.hurrypizza.mine.config.security.UserInfo;
import com.hurrypizza.mine.exception.LoginFailedException;
import com.hurrypizza.mine.exception.ResourceAlreadyExistException;
import com.hurrypizza.mine.exception.ResourceNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
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
    public User validateUserByEmailAndPassword(final String email, final String password)
            throws AuthenticationException {
        var user = findByEmail(email);
        if (!encoder.matches(password, user.getPassword())) {
            throw new LoginFailedException("Password is wrong");
        }
        return user;
    }

    @Transactional
    @Override
    public UserInfo save(String email, String password, String color, String nickname) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResourceAlreadyExistException("Email: %s".formatted(email));
        }
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new ResourceAlreadyExistException("Nickname: %s".formatted(nickname));
        }
        var user = userRepository.save(User.createNormalUser(email, password, color, nickname));
        return UserInfo.from(user);
    }

    private User findByEmail(final String email) {
        return userRepository.findByEmail(email)
                       .orElseThrow(ResourceNotExistException::new);
    }

}
