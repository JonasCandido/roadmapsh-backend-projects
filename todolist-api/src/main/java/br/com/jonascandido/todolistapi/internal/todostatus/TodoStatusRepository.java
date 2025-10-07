package br.com.jonascandido.todolistapi.internal.todostatus;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoStatusRepository extends JpaRepository<TodoStatus, Integer> {
    
}
