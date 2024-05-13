package com.example.chatgpttodoapp.service;

import com.example.chatgpttodoapp.model.TodoItem;
import com.example.chatgpttodoapp.model.User;
import com.example.chatgpttodoapp.repository.TodoItemRepository;
import com.example.chatgpttodoapp.service.impl.TodoItemServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

// TodoItemServiceTest.java
@RunWith(MockitoJUnitRunner.class)
public class TodoItemServiceTest {
    @Mock
    private TodoItemRepository todoItemRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TodoItemServiceImpl todoItemService;

    @Test
    public void testGetAllItemsForUser() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        List<TodoItem> items = new ArrayList<>();
        TodoItem item1 = new TodoItem();
        item1.setId(1L);
        item1.setTitle("Item 1");
        item1.setUser(user);
        TodoItem item2 = new TodoItem();
        item2.setId(2L);
        item2.setTitle("Item 2");
        item2.setUser(user);
        items.add(item1);
        items.add(item2);
        when(todoItemRepository.findByUser_Username(username)).thenReturn(items);

        List<TodoItem> result = todoItemService.getAllItemsForUser(username);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetItemByIdForUser() {
        Long itemId = 1L;
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        TodoItem item = new TodoItem();
        item.setId(itemId);
        item.setTitle("Item 1");
        item.setUser(user);
        when(todoItemRepository.findById(itemId)).thenReturn(java.util.Optional.of(item));

        TodoItem result = todoItemService.getItemByIdForUser(itemId, username);

        assertNotNull(result);
        assertEquals(itemId, result.getId());
    }

    @Test
    public void testCreateItemForUser() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        TodoItem newItem = new TodoItem();
        newItem.setTitle("New Item");
        when(userService.getUserByUsername(username)).thenReturn(user);
        when(todoItemRepository.save(newItem)).thenReturn(newItem);

        TodoItem result = todoItemService.createItemForUser(newItem, username);

        assertNotNull(result);
        assertEquals(newItem.getTitle(), result.getTitle());
        assertEquals(user, result.getUser());
    }

    @Test
    public void testUpdateItemForUser() {
        Long itemId = 1L;
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        TodoItem existingItem = new TodoItem();
        existingItem.setId(itemId);
        existingItem.setTitle("Old Item");
        existingItem.setUser(user);
        TodoItem newItem = new TodoItem();
        newItem.setTitle("New Item");
        when(todoItemRepository.findById(itemId)).thenReturn(java.util.Optional.of(existingItem));
        when(todoItemRepository.save(existingItem)).thenReturn(existingItem);

        TodoItem result = todoItemService.updateItemForUser(itemId, newItem, username);

        assertNotNull(result);
        assertEquals(newItem.getTitle(), result.getTitle());
    }

    @Test
    public void testDeleteItemForUser() {
        Long itemId = 1L;
        String username = "testuser";

        // Create a user
        User user = new User();
        user.setUsername(username);

        // Create a TodoItem and set the user
        TodoItem existingItem = new TodoItem();
        existingItem.setId(itemId);
        existingItem.setTitle("Item to delete");
        existingItem.setUser(user); // Set the user

        // Mock the repository method call to return the existing item
        when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingItem));

        // Call the service method to delete the item
        todoItemService.deleteItemForUser(itemId, username);

        // Verify that the delete method of the repository was called with the existing item
        verify(todoItemRepository, times(1)).delete(existingItem);
    }

}

