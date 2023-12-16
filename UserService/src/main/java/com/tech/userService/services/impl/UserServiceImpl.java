package com.tech.userService.services.impl;

import com.tech.userService.entities.externalEntities.Entreprise;
import com.tech.userService.entities.externalEntities.Rating;
import com.tech.userService.entities.User;
import com.tech.userService.exceptions.ResourceNotFoundException;
import com.tech.userService.external.services.EntrepriseService;
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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final EntrepriseService entrepriseService;

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
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

            if (ratingsOfUser != null && ratingsOfUser.length > 0) {
                List<Rating> ratingList = Arrays.asList(ratingsOfUser);

                ratingList.forEach(rating -> {
                    ResponseEntity<Entreprise> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Entreprise.class);

                    if (hotelEntity.getStatusCode().is2xxSuccessful()) {
                        Entreprise entreprise = hotelEntity.getBody();
                        rating.setEntreprise(entreprise);
                    } else {
                        logger.warn("Hôtel non trouvé pour le ratingService {}", rating.getRatingId());
                    }
                });

                user.setRatings(ratingList);
            }
        });
        return users;
    }

    /**
     * @param userId
     * @return
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
        *API Call to entreprise Service to get the entreprise
        * set the entreprise to the ratingService
        * return the ratingService.
        */
        // http://localhost:8082/hotels/id/
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to entreprise userService to get the entreprise
            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791

            //ResponseEntity<Entreprise> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ratingService.getHotelId(), Entreprise.class);
            //Entreprise entreprise = hotelEntity.getBody();

            Entreprise entreprise = entrepriseService.getHotel(rating.getHotelId());

            //logger.info("response status code: {} ",hotelEntity.getStatusCode());

            //set the entreprise to ratingService
            rating.setEntreprise(entreprise);

            //return the ratingService
            return rating;
        }).toList();

        user.setRatings(ratingList);
        return user;
    }
}
