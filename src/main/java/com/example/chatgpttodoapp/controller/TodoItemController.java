package com.example.chatgpttodoapp.controller;

import com.example.chatgpttodoapp.model.TodoItem;
import com.example.chatgpttodoapp.service.TodoItemService;
import com.example.chatgpttodoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoItemController {
    private final TodoItemService todoItemService;
    private final UserService userService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService, UserService userService) {
        this.todoItemService = todoItemService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllItemsForUser(Principal principal) {
        String username = principal.getName();
        List<TodoItem> items = todoItemService.getAllItemsForUser(username);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getItemByIdForUser(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        TodoItem item = todoItemService.getItemByIdForUser(id, username);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TodoItem> createItemForUser(@RequestBody TodoItem item, Principal principal) {
        String username = principal.getName();
        TodoItem newItem = todoItemService.createItemForUser(item, username);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateItemForUser(@PathVariable Long id, @RequestBody TodoItem item, Principal principal) {
        String username = principal.getName();
        TodoItem updatedItem = todoItemService.updateItemForUser(id, item, username);
        if (updatedItem != null) {
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemForUser(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        todoItemService.deleteItemForUser(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
