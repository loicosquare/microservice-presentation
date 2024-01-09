package com.tech.userService.services.impl;

import com.tech.userService.external.entities.Enterprise;
import com.tech.userService.external.entities.Rating;
import com.tech.userService.entities.User;
import com.tech.userService.exceptions.ResourceNotFoundException;
import com.tech.userService.external.services.EnterpriseService;
import com.tech.userService.repository.UserRepository;
import com.tech.userService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final EnterpriseService enterpriseService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * @param user
     * @return User
     */
    @Override
    public User saveUser(User user) {
        User user1 = User.builder()
                .userId(UUID.randomUUID().toString())
                .name(user.getName())
                .email(user.getEmail())
                .about(user.getAbout())
                .build();
        return userRepository.save(user1);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

            if (ratingsOfUser != null && ratingsOfUser.length > 0) {
                List<Rating> ratingList = Arrays.asList(ratingsOfUser);

                ratingList.forEach(rating -> {
                    ResponseEntity<Enterprise> enterpriseEntity = restTemplate.getForEntity("http://ENTERPRISE-SERVICE/enterprises/" + rating.getEnterpriseId(), Enterprise.class);

                    if (enterpriseEntity.getStatusCode().is2xxSuccessful()) {
                        Enterprise enterprise = enterpriseEntity.getBody();
                        rating.setEnterprise(enterprise);
                    } else {
                        logger.warn("Enterprise non trouvée pour le ratingService {}", rating.getRatingId());
                    }
                });

                user.setRatings(ratingList);
            }
        });
        return users;
    }

    /**
     * @param userId
     * @return User
     */
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: {} " + userId));
        //Fetch Rating for the above user
        //http://localhost:8083/ratings/users/uuid/

        //Etape 5 de notre document sur les microservices
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class); //The last param is the class type of the response (expected type).
        logger.info("Ratings of user: {}", ratingsOfUser);

        /*
        *API Call to enterprise Service to get the enterprise
        * set the enterprise to the ratingService
        * return the ratingService.
        * TODO: Add game entity and set it to the user.
        */
        // http://localhost:8082/enterprises/id/
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to enterprise userService to get the enterprise
            //http://localhost:8082/enterprises/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791

            //ResponseEntity<Enterprise> enterpriseEntity = restTemplate.getForEntity("http://ENTERPRISE-SERVICE/enterprises/"+ratingService.getEnterpriseId(), Enterprise.class);
            //Enterprise enterprise = enterpriseEntity.getBody();

            Enterprise enterprise = enterpriseService.getEnterprise(rating.getEnterpriseId());

            //logger.info("response status code: {} ",enterpriseEntity.getStatusCode());

            //set the enterprise to ratingService
            rating.setEnterprise(enterprise);

            //return the ratingService
            return rating;
        }).toList();

        user.setRatings(ratingList);
        return user;
    }
}
