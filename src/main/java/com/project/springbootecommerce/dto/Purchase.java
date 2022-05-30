package com.project.springbootecommerce.dto;

import com.project.springbootecommerce.entity.Address;
import com.project.springbootecommerce.entity.Customer;
import com.project.springbootecommerce.entity.Order;
import com.project.springbootecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
