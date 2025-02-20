package com.dinidu;

import com.dinidu.config.FactoryConfiguration;
import com.dinidu.entity.Customer;
import com.dinidu.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        List<Order> orders = new ArrayList<>();
        Customer customer = new Customer();
        customer.setName("Dinidu");

        Order order1 = new Order();
        order1.setOrderDate("2025-02-15");
        order1.setCustomer(customer);

        Order order2 = new Order();
        order2.setOrderDate("2025-02-20");
        order2.setCustomer(customer);

        orders.add(order1);
        orders.add(order2);
        customer.setOrders(orders);

        session.save(customer);
        transaction.commit();
        session.close();
    }
}