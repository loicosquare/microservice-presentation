package com.tech.user.service.services.impl;

import com.tech.user.service.entities.Rating;
import com.tech.user.service.entities.User;
import com.tech.user.service.exceptions.ResourceNotFoundException;
import com.tech.user.service.repository.UserRepository;
import com.tech.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.glassfish.jaxb.core.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: {} " + userId));
        //TODO: Fetch Rating for the above user
        // http://localhost:8083/ratings/users/uuid/

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class); //The last param is the class type of the response (expected type).
        logger.info("Ratings of user: {}", ratingsOfUser);
        return user;
    }
}
