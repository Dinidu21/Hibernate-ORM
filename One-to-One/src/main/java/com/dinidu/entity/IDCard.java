package com.dinidu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.smartcardio.Card;

@Entity
@Table(name = "nic_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IDCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardNo;

    @OneToOne(mappedBy = "card")
    private Customer customer;
}
