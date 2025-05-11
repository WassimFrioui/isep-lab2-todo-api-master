package org.isep.cleancode;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class TodoController {

    // this Todo class should be completed to achieve Step 1

    private static final Gson gson = new Gson();
    private final List<Todo> todos = new ArrayList<>();

    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");

        return gson.toJson(todos);
    };

    public Object createTodo(Request req, Response res) {
        Todo newTodo = gson.fromJson(req.body(), Todo.class);

        if (newTodo.getName() == null || newTodo.getName().isEmpty() || newTodo.getName().length() > 64) {
            res.status(400);
            return "Todo name is required and should not exceed 64 characters";
        }

        // dueDate String
        if (newTodo.getDueDate() == null || newTodo.getDueDate().isEmpty()) {
            res.status(400);
            return "Due date is required";
        }


        for (Todo todo : todos) {
            if (todo.getName().equals(newTodo.getName())) {
                res.status(400);
                return "Todo with this name already exists";
            }
        }


        todos.add(newTodo);
        res.status(201);
        res.type("application/json");

        return gson.toJson(newTodo);
    };
}
