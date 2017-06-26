
package com.mycompany.TestTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author YARUS
 */
@Controller
public class MainController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getContacts(Model model){
        
             
        return "/index";
    }
    
    
    
}
