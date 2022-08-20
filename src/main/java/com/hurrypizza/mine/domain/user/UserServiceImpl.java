package com.hurrypizza.mine.domain.user;

import com.hurrypizza.mine.domain.projection.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                       .map(UserResponse::from)
                       .toList();
    }

}
