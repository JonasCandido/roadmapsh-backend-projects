package br.com.jonascandido.todolistapi.internal;

import br.com.jonascandido.todolistapi.internal.todo.*;
import br.com.jonascandido.todolistapi.internal.todostatus.*;
import br.com.jonascandido.todolistapi.internal.user.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private TodoRepository todoRepository;
    private UserRepository userRepository;
    private TodoStatusRepository todoStatusRepository;
    private TodoService todoService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        todoStatusRepository = mock(TodoStatusRepository.class);
    	todoRepository = mock(TodoRepository.class);
        userRepository = mock(UserRepository.class);
        todoService = new TodoService(todoRepository, todoStatusRepository, userRepository);
        userService = new UserService(userRepository);
    }

    @Test
    void testAddTodo_Success() {
        User user = new User("Charlie", "charlie@example.com", "123456");
        TodoStatus pendingStatus = new TodoStatus("Pending");
        Todo todo = new Todo("Laundry", "Do laundry", user, pendingStatus);

        when(userRepository.existsById(user.getId())).thenReturn(true);
        when(todoStatusRepository.findById(pendingStatus.getId())).thenReturn(Optional.of(pendingStatus));
        when(todoRepository.save(todo)).thenReturn(todo);

        Todo savedTodo = todoService.createTodo(todo);

        assertEquals(todo.getTitle(), savedTodo.getTitle());
        assertEquals(user, savedTodo.getUser());
        assertEquals(pendingStatus, savedTodo.getStatus());

        verify(userRepository).existsById(user.getId());
        verify(todoStatusRepository).findById(pendingStatus.getId());
        verify(todoRepository).save(todo);
    }

}
