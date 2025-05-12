package org.isep.cleancode;

import static spark.Spark.*;

import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.persistence.csvfiles.TodoCsvFilesRepository;
import org.isep.cleancode.persistence.inmemory.TodoInMemoryRepository;
import org.isep.cleancode.presentation.TodoController;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        port(4567);
        TodoInMemoryRepository repository = new TodoInMemoryRepository(); // (Useless in at the Step 4, Use cvsRepository)
        TodoCsvFilesRepository csvRepository = new TodoCsvFilesRepository();
        TodoManager manager = new TodoManager(csvRepository);
        TodoController controller = new TodoController(manager);


        get("/todos", controller::getAllTodos);
        post("/todos", controller::createTodo);
    }
}

