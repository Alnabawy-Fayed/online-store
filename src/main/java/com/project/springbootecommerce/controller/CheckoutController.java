package com.project.springbootecommerce.controller;

import com.project.springbootecommerce.dto.Purchase;
import com.project.springbootecommerce.dto.PurchaseResponse;
import com.project.springbootecommerce.service.CheckoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/checkout")
public class CheckoutController {
    CheckoutServiceImpl checkoutService;
    @Autowired
    public CheckoutController(CheckoutServiceImpl checkoutService){
        this.checkoutService = checkoutService;
    }
    @PostMapping("/purchase")
    public PurchaseResponse makeOrder(@RequestBody Purchase purchase){
        PurchaseResponse purchaseResponse = checkoutService.makeOrder(purchase);
        return purchaseResponse;
    }
}
