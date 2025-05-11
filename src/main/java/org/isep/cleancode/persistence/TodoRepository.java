package org.isep.cleancode.persistence;

import java.util.ArrayList;
import java.util.List;

import org.isep.cleancode.models.Todo;

public class TodoRepository {
        private final List<Todo> todos = new ArrayList<>();

        public List<Todo> getAllTodos() {
                return todos;
        }

        public void saveTodos(Todo todo) {
                todos.add(todo);
        }

        public boolean existsByName(String name) {
            for (Todo todo : todos) {
                if (todo.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public Todo getTodoByName(String name) {
            for (Todo todo : todos) {
                if (todo.getName().equals(name)) {
                    return todo;
                }
            }
            return null;
        }
}
