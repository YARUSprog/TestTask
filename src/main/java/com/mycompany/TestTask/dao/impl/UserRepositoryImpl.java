
package com.mycompany.TestTask.dao.impl;

import com.mycompany.TestTask.model.User;
import com.mycompany.TestTask.exception.UserAlreadyExistsException;
import com.mycompany.TestTask.dao.UserRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author YARUS
 */
@Component
public class UserRepositoryImpl implements UserRepository{

    private User user;
    
    public UserRepositoryImpl(){
 
    }
    
    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        user.setId(1L);        
        if(this.user == null)
            this.user = user;
        else
            throw new UserAlreadyExistsException();
        return user;
    }
    
}
