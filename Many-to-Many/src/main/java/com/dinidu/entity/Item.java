package com.dinidu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data

// Inverse side
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // auto Id generated - hibernate decide
    private int id;
    private String name;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;

}
