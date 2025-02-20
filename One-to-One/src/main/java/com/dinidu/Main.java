package com.dinidu;

import com.dinidu.config.FactoryConfiguration;
import com.dinidu.entity.Customer;
import com.dinidu.entity.IDCard;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSessionFactory();
        session.close();

        Customer customer = new Customer(
                1,
                "",
                new IDCard()
        );

        customer.setCard(new IDCard());

    }
}