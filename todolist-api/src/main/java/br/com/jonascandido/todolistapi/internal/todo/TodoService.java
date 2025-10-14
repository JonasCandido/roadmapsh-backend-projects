package br.com.jonascandido.todolistapi.internal.todo;

import br.com.jonascandido.todolistapi.internal.todo.Todo;
import br.com.jonascandido.todolistapi.internal.todostatus.TodoStatus;
import br.com.jonascandido.todolistapi.internal.todo.TodoRepository;
import br.com.jonascandido.todolistapi.internal.todostatus.TodoStatusRepository;
import br.com.jonascandido.todolistapi.internal.user.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoStatusRepository todoStatusRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository,
                       TodoStatusRepository todoStatusRepository,
                       UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.todoStatusRepository = todoStatusRepository;
        this.userRepository = userRepository;
    }

    
}
