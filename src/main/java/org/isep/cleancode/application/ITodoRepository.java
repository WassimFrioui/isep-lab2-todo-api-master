package org.isep.cleancode.application;


import java.util.List;
import org.isep.cleancode.models.Todo;

public interface ITodoRepository {
    void saveTodos(Todo todo);
    List<Todo> getAllTodos();
    boolean existsByName(String name);
}
