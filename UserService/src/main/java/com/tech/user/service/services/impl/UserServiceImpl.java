package com.tech.user.service.services.impl;

import com.tech.user.service.entities.User;
import com.tech.user.service.exceptions.ResourceNotFoundException;
import com.tech.user.service.repository.UserRepository;
import com.tech.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll().stream().toList();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: {} " + userId));
    }
}
