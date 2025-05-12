package org.isep.cleancode.application;

import java.util.List;

import org.isep.cleancode.models.Todo;
import org.isep.cleancode.application.ITodoRepository;

public class TodoManager {
        private final ITodoRepository itodoRepository;


    public TodoManager(ITodoRepository itodoRepository) {
        this.itodoRepository = itodoRepository;
    }

    public List<Todo> getAllTodos() {
        return itodoRepository.getAllTodos();
    }

    public void createTodo(Todo todo) {
        if (todo.getName() == null || todo.getName().isEmpty() || todo.getName().length() > 64) {
            throw new IllegalArgumentException("Todo name is required and should not exceed 64 characters");
        }

        // dueDate String
        if (todo.getDueDate() == null || todo.getDueDate().isEmpty()) {
            throw new IllegalArgumentException("Due date is required");
        }

        if (itodoRepository.existsByName(todo.getName())) {
            throw new IllegalArgumentException("Todo with this name already exists");
        }

        itodoRepository.saveTodos(todo);
    }


    
}
