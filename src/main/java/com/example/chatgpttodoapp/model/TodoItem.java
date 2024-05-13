package com.example.chatgpttodoapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "todo_items")
@Data
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}