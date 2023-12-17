package com.tech.userService.controller;

import com.tech.userService.entities.User;
import com.tech.userService.services.UserService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    int retryCount = 0;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User user1 = User.builder()
//                .userId(UUID.randomUUID().toString())
//                .name(user.getName())
//                .email(user.getEmail())
//                .about(user.getAbout())
//                .build();
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user get
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingEnterpriseCircuitBreaker", fallbackMethod = "ratingEnterpriseFallback")
    @Retry(name = "ratingEnterpriseService", fallbackMethod = "ratingEnterpriseFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {

        logger.info("Get Single User Handler: UserController");
        logger.info("Retry count: {}", retryCount);
        retryCount++;

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    public ResponseEntity<User> ratingEnterpriseFallback(String userId, Exception ex) { //The retrun type of the fallback Method should be the same as the method that returns the user object.
//        logger.info("Fallback is executed because userService is down : ", ex.getMessage());
        ex.printStackTrace();

        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some userService is down").userId("141234").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }


    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        if (allUser != null && !allUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(allUser);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
