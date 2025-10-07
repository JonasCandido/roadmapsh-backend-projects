package br.com.jonascandido.todolistapi.internal;

import br.com.jonascandido.todolistapi.internal.user.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)

class UserControllerSliceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldCreateUser() throws Exception {

        User user = new User("Paulo", "paulo@example.com", "123456");
        
        given(userService.addUser(any(User.class))).willReturn(user);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value("Paulo"));

        verify(userService, times(1)).addUser(any(User.class));
    }
}
