
package com.mycompany.TestTask.service.impl;

import com.mycompany.TestTask.dao.UserRepository;
import com.mycompany.TestTask.exception.UserAlreadyExistsException;
import com.mycompany.TestTask.model.User;
import com.mycompany.TestTask.model.dto.UserDto;
import com.mycompany.TestTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YARUS
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User createUser(UserDto userDto) throws UserAlreadyExistsException {
        User user = dtoToModel(userDto);
        userRepository.createUser(user);
        return user;
    }
    
    private User dtoToModel(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMiddleName(userDto.getMiddleName());
        user.setPassword(userDto.getPassword());
        return user;
    }
    
}
