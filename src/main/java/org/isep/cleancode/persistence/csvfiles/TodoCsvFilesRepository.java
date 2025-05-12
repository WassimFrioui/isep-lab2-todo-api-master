package org.isep.cleancode.persistence.csvfiles;

import org.isep.cleancode.models.Todo;
import org.isep.cleancode.application.ITodoRepository;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoCsvFilesRepository implements ITodoRepository {

    private final Path filePath;

    public TodoCsvFilesRepository() {
        String appDataPath = System.getenv("APPDATA");
        if (appDataPath == null) {
            appDataPath = System.getProperty("user.home");
        }
        this.filePath = Paths.get(appDataPath, "todos.csv");
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to create CSV file", e);
        }
    }

    @Override
    public void saveTodos(Todo todo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(todo.getName() + "," + (todo.getDueDate() != null ? todo.getDueDate() : ""));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Unable to write todo to file", e);
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                String name = parts[0];
                String dueDate = parts.length > 1 ? parts[1] : null;
                todos.add(new Todo(name, dueDate));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read todos from file", e);
        }
        return todos;
    }

    @Override
    public boolean existsByName(String name) {
        return getAllTodos().stream().anyMatch(todo -> todo.getName().equals(name));
    }
}
