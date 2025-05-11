package org.isep.cleancode;

// import java.time.LocalDate;

public class Todo {

    // this Todo class should be completed to achieve Step 1

    private String name;
    private String dueDate; // I out this as String because i have a problem with LocalDate

    public Todo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}
