package org.isep.cleancode.presentation;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.models.Todo;


public class TodoController {

    private final Gson gson = new Gson();
    private final TodoManager todoManager;

    public TodoController(TodoManager todoManager) {
        this.todoManager = todoManager;
    }

    public String getAllTodos(Request request, Response response) {
        List<Todo> todos = todoManager.getAllTodos();
        return gson.toJson(todos);
    }

    public String createTodo(Request request, Response response) {
        Todo todo = gson.fromJson(request.body(), Todo.class);
        try {
            todoManager.createTodo(todo);
            response.status(201); // Created
            return gson.toJson(todo);
        } catch (IllegalArgumentException e) {
            response.status(400); // Bad Request
            return gson.toJson(e.getMessage());
        }
    }

}
