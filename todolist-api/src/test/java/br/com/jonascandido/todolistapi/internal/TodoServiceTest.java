package br.com.jonascandido.todolistapi.internal;

import br.com.jonascandido.todolistapi.internal.todo.*;
import br.com.jonascandido.todolistapi.internal.todostatus.*;
import br.com.jonascandido.todolistapi.internal.user.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private TodoRepository todoRepository;
    private UserRepository userRepository;
    private TodoStatusRepository todoStatusRepository;
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        todoStatusRepository = mock(TodoStatusRepository.class);
	todoRepository = mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

}