
package com.mycompany.TestTask.controller.rest;

import com.mycompany.TestTask.exception.UserAlreadyExistsException;
import com.mycompany.TestTask.model.User;
import com.mycompany.TestTask.model.dto.UserDto;
import com.mycompany.TestTask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YARUS
 */
@RestController
@RequestMapping("/userservice")
public class UserRestController {
    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;  
    
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;        
    }
    
    @PostMapping("/register")   
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws UserAlreadyExistsException{       
        User user = userService.createUser(userDto);        

        if (user.getId() > 0) {
            log.info("User with id: " + user.getId() + " successful created.");             
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        log.error("User was not created.");        
        HttpHeaders headers = new HttpHeaders();
        headers.set("description", "Server error");       
        return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);        
    }
}
