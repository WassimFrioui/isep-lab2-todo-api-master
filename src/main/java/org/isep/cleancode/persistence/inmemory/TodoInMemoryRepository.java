package org.isep.cleancode.persistence.inmemory;

import java.util.ArrayList;
import java.util.List;

import org.isep.cleancode.models.Todo;
import org.isep.cleancode.application.ITodoRepository;

public class TodoInMemoryRepository implements ITodoRepository {
        private final List<Todo> todos = new ArrayList<>();

        @Override
        public List<Todo> getAllTodos() {
                return todos;
        }

        @Override
        public void saveTodos(Todo todo) {
                todos.add(todo);
        }

        @Override
        public boolean existsByName(String name) {
            for (Todo todo : todos) {
                if (todo.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }

}
