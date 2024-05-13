package com.example.chatgpttodoapp.service;

import com.example.chatgpttodoapp.model.TodoItem;

import java.util.List;

public interface TodoItemService {
    List<TodoItem> getAllItemsForUser(String username);
    TodoItem getItemByIdForUser(Long id, String username);
    TodoItem createItemForUser(TodoItem item, String username);
    TodoItem updateItemForUser(Long id, TodoItem newItem, String username);
    void deleteItemForUser(Long id, String username);
}