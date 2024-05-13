package com.example.chatgpttodoapp.service.impl;

import com.example.chatgpttodoapp.model.TodoItem;
import com.example.chatgpttodoapp.model.User;
import com.example.chatgpttodoapp.repository.TodoItemRepository;
import com.example.chatgpttodoapp.service.TodoItemService;
import com.example.chatgpttodoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemRepository todoItemRepository;
    private final UserService userService;

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository todoItemRepository, UserService userService) {
        this.todoItemRepository = todoItemRepository;
        this.userService = userService;
    }

    @Override
    public List<TodoItem> getAllItemsForUser(String username) {
        return todoItemRepository.findByUser_Username(username);
    }

    @Override
    public TodoItem getItemByIdForUser(Long id, String username) {
        return todoItemRepository.findById(id)
                .filter(item -> item.getUser().getUsername().equals(username))
                .orElse(null);
    }

    @Override
    public TodoItem createItemForUser(TodoItem item, String username) {
        User user = userService.getUserByUsername(username);
        item.setUser(user);
        return todoItemRepository.save(item);
    }

    @Override
    public TodoItem updateItemForUser(Long id, TodoItem newItem, String username) {
        TodoItem existingItem = getItemByIdForUser(id, username);
        if (existingItem != null) {
            existingItem.setTitle(newItem.getTitle());
            existingItem.setDescription(newItem.getDescription());
            return todoItemRepository.save(existingItem);
        }
        return null;
    }

    @Override
    public void deleteItemForUser(Long id, String username) {
        TodoItem existingItem = getItemByIdForUser(id, username);
        if (existingItem != null) {
            todoItemRepository.delete(existingItem);
        }
    }
}