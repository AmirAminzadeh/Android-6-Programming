package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

public class ToDoListManager {

    private List<ToDoItem> items;

    public ToDoListManager() {
        items = new ArrayList<ToDoItem>();

        items.add(new ToDoItem("Get Milk", false, 1));
        items.add(new ToDoItem("Walk the dog", true, 1));
        items.add(new ToDoItem("Go to the gym", false, 1));
    }

    public List<ToDoItem> getItems() {
        return items;
    }

    public void addItem(ToDoItem item) {
        items.add(item);
    }


    public void removeItem() {
        items.remove(0);
    }
}
