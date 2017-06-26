
package com.mycompany.TestTask.controller.rest;

import com.mycompany.TestTask.model.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import org.junit.After;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 *
 * @author YARUS
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserRestControllerTest {
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);
        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
      
    public UserRestControllerTest() {}
    
    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerUser method, of class UserRestController.
     */
    @Test
    public void testRegisterUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("first_name");
        userDto.setMiddleName("midle_name");
        userDto.setLastName("last_name");
        userDto.setPassword("somePass212");
        
        String userJson = json(userDto);
        
        //The first time an object is created, and the http status says about it
        this.mockMvc.perform(post("/userservice/register")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("first_name")))
                .andExpect(jsonPath("$.middleName", is("midle_name")))
                .andExpect(jsonPath("$.lastName", is("last_name")));
        
        //We are trying to create an existing user and get the status of 409
        this.mockMvc.perform(post("/userservice/register")
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().is(409))                
                .andExpect(jsonPath("$.code", is("USER_ALREADY_EXISTS")))
                .andExpect(jsonPath("$.description", is("A user with the given username already exists")));
    }
    
    protected String json(UserDto o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
