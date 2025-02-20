package com.dinidu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // auto Id generated - hibernate decide
    private int id;
    private String name;

    @OneToOne
    @JoinColumn(name = "NIC_No")
    private IDCard card;

}
