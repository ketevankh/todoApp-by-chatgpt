package com.example.chatgpttodoapp.controller;


import com.example.chatgpttodoapp.model.TodoItem;
import com.example.chatgpttodoapp.service.TodoItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemControllerTest {
    @Mock
    private TodoItemService todoItemService;

    @Mock
    private Principal principal;

    @InjectMocks
    private TodoItemController todoItemController;

    @Test
    public void testGetAllItemsForUser() {
        String username = "testuser";
        List<TodoItem> items = new ArrayList<>();
        when(todoItemService.getAllItemsForUser(username)).thenReturn(items);

        ResponseEntity<List<TodoItem>> response = todoItemController.getAllItemsForUser(() -> username);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testGetItemByIdForUser() {
        Long itemId = 1L;
        String username = "testuser";
        TodoItem item = new TodoItem();
        when(todoItemService.getItemByIdForUser(itemId, username)).thenReturn(item);

        ResponseEntity<TodoItem> response = todoItemController.getItemByIdForUser(itemId, () -> username);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    public void testCreateItemForUser() {
        String username = "testuser";
        TodoItem item = new TodoItem();
        when(todoItemService.createItemForUser(item, username)).thenReturn(item);

        ResponseEntity<TodoItem> response = todoItemController.createItemForUser(item, () -> username);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    public void testUpdateItemForUser() {
        Long itemId = 1L;
        String username = "testuser";
        TodoItem item = new TodoItem();
        when(todoItemService.updateItemForUser(itemId, item, username)).thenReturn(item);

        ResponseEntity<TodoItem> response = todoItemController.updateItemForUser(itemId, item, () -> username);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    public void testDeleteItemForUser() {
        Long itemId = 1L;
        String username = "testuser";
        doNothing().when(todoItemService).deleteItemForUser(itemId, username);

        ResponseEntity<Void> response = todoItemController.deleteItemForUser(itemId, () -> username);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}

