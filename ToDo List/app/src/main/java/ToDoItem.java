package com.example.todolist;

public class ToDoItem {

    private String description;
    private boolean isComplete;
    private int isClicked;


    public ToDoItem(String description, boolean isComplete, int isClicked) {
        this.description = description;
        this.isComplete = isComplete;
        this.isClicked = isClicked;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public int isClicked(){ return isClicked; }

    public void toggleComplete() { isComplete = !isComplete; }

    @Override
    // any object that this method is applied on, will then be returned as a string object
    public String toString() {
        return getDescription();
    }
}
