/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.TestTask.service;

import com.mycompany.TestTask.exception.UserAlreadyExistsException;
import com.mycompany.TestTask.model.User;
import com.mycompany.TestTask.model.dto.UserDto;

/**
 *
 * @author YARUS
 */
public interface UserService {
    User createUser( UserDto user ) throws UserAlreadyExistsException;
}
