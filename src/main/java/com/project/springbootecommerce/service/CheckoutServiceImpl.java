package com.project.springbootecommerce.service;

import com.project.springbootecommerce.dao.CustomerRepository;
import com.project.springbootecommerce.dto.Purchase;
import com.project.springbootecommerce.dto.PurchaseResponse;
import com.project.springbootecommerce.entity.Customer;
import com.project.springbootecommerce.entity.Order;
import com.project.springbootecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    CustomerRepository customerRepository;
    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    public PurchaseResponse makeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        Set<OrderItem> items = purchase.getOrderItems();
        for(OrderItem item : items){
            order.add(item);
        }
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        order.setShippingAddress(purchase.getShippingAddress());
        order.setBillingAddress(purchase.getBillingAddress());
        Customer customer = purchase.getCustomer();
        //check the existence of this customer
        Customer customerFromDb = customerRepository.findByEmail(customer.getEmail());
        if(customerFromDb != null){
            customer = customerFromDb;
        }
        customer.add(order);
        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
