package com.example.demo.controller;

import com.example.demo.exception.TodoNotFoundException;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    @GetMapping
    public List<Todo> getAllTodos(Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        return todoRepository.findByOwner(currentUser);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        if (!todo.getOwner().getId().equals(currentUser.getId())) {
            throw new TodoNotFoundException(id);
        }

        return todo;
    }

    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        todo.setOwner(currentUser);
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo updatedTodo, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        if (!todo.getOwner().getId().equals(currentUser.getId())) {
            throw new TodoNotFoundException(id);
        }

        todo.setTitle(updatedTodo.getTitle());
        todo.setCompleted(updatedTodo.isCompleted());

        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        if (!todo.getOwner().getId().equals(currentUser.getId())) {
            throw new TodoNotFoundException(id);
        }

        todoRepository.deleteById(id);
    }
}