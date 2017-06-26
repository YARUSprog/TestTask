
package com.mycompany.TestTask.exception;

import com.mycompany.TestTask.model.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author YARUS
 */
@ControllerAdvice
public class GlobalExceptionHandling {
    protected static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandling.class);
    
    public GlobalExceptionHandling() {}
    
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUnsupportedChangingStatus(UserAlreadyExistsException exception) {
        log.error(exception.getMessage(), exception);
        ResultDto resultDto = new ResultDto("USER_ALREADY_EXISTS", "A user with the given username already exists");        
        return new ResponseEntity<>(resultDto, HttpStatus.valueOf(409));
        
    }    
}
