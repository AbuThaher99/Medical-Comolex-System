package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Core.Repsitory.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }



}
