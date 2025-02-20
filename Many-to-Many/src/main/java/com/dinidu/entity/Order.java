package com.dinidu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents an Order in the system.
 * <p>
 * This class is mapped to the "orders" table in the database and contains order-related information,
 * such as the order date and the list of items associated with the order.
 * </p>
 * <p>
 * The relationship between Orders and Items is a Many-to-Many relationship, where each order can
 * have multiple items and each item can appear in multiple orders.
 * </p>
 * <p>
 * Annotations used:
 * </p>
 *
 * <ul>
 *     <li><b>@Entity</b> - Marks this class as a JPA entity that is mapped to a database table.</li>
 *     <li><b>@Table(name = "orders")</b> - Specifies the name of the database table ("orders") that
 *     this entity is mapped to. The default is the class name in lowercase.</li>
 *     <li><b>@Id</b> - Indicates the primary key field of the entity.</li>
 *     <li><b>@GeneratedValue(strategy = GenerationType.IDENTITY)</b> - Specifies that the primary key
 *     should be automatically generated by the database with an auto-increment strategy.</li>
 *     <li><b>@ManyToMany</b> - Defines a many-to-many relationship between the "Order" and "Item"
 *     entities, meaning an order can have multiple items, and an item can belong to multiple orders.</li>
 *     <li><b>@JoinTable</b> - Defines the join table for the many-to-many relationship. It specifies the
 *     name of the join table ("order_details"), the foreign key column for the "Order" entity
 *     ("oder_id"), and the foreign key column for the "Item" entity ("item_id").</li>
 *     <li><b>@JoinColumn</b> - Specifies the foreign key columns in the join table. One for the owning side
 *     (Order) and one for the inverse side (Item).</li>
 *     <li><b>@AllArgsConstructor</b> - Generates a constructor with arguments for all fields in the class.</li>
 *     <li><b>@NoArgsConstructor</b> - Generates a no-argument constructor for the class.</li>
 *     <li><b>@Data</b> - Generates getters, setters, toString(), equals(), and hashCode() methods
 *     for the class, reducing boilerplate code.</li>
 * </ul>
 *
 * @see Item
 */
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderDate;

    /**
     * List of items that are part of the order.
     *
     * This relationship is defined as Many-to-Many, where an order can contain
     * multiple items, and an item can be part of multiple orders.
     */

    @ManyToMany
    @JoinTable(
            name = "order_details",
            joinColumns = @JoinColumn(name = "oder_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;
}
