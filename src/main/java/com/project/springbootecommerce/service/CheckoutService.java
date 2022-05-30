package com.project.springbootecommerce.service;

import com.project.springbootecommerce.dto.Purchase;
import com.project.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse makeOrder(Purchase purchase);
}
