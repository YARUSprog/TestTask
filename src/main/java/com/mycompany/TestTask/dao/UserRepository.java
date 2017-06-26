
package com.mycompany.TestTask.dao;

import com.mycompany.TestTask.exception.UserAlreadyExistsException;
import com.mycompany.TestTask.model.User;

/**
 *
 * @author YARUS
 */
public interface UserRepository {
    User createUser( User user ) throws UserAlreadyExistsException;
}
