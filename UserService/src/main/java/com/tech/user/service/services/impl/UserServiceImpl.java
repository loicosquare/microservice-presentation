package com.tech.user.service.services.impl;

import com.tech.user.service.entities.external.Hotel;
import com.tech.user.service.entities.external.Rating;
import com.tech.user.service.entities.User;
import com.tech.user.service.exceptions.ResourceNotFoundException;
import com.tech.user.service.repository.UserRepository;
import com.tech.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

            if (ratingsOfUser != null && ratingsOfUser.length > 0) {
                List<Rating> ratingList = Arrays.asList(ratingsOfUser);

                ratingList.forEach(rating -> {
                    ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getRatingId(), Hotel.class);

                    if (hotelEntity.getStatusCode().is2xxSuccessful()) {
                        Hotel hotel = hotelEntity.getBody();
                        rating.setHotel(hotel);
                    } else {
                        logger.warn("Hôtel non trouvé pour le rating {0}" + rating.getRatingId());
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
        *API Call to hotel Service to get the hotel
        * set the hotel to the rating
        * return the rating
        */
        // http://localhost:8082/hotels/id/
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791

            ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelEntity.getBody();
            //Hotel hotel = hotelService.getHotel(rating.getHotelId());

            logger.info("response status code: {} ",hotelEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);

            //return the rating
            return rating;
        }).toList();

        user.setRatings(ratingList);
        return user;
    }
}
