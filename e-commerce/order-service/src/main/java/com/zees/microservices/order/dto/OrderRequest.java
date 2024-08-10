package com.zees.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id,String orderNumber, String skuCode, BigDecimal price, Integer quantity, UserDetails userDetails) {
    public OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity, UserDetails userDetails) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
        this.userDetails = userDetails != null ? userDetails : new UserDetails("testUser@gmail.com", "test1", "user1");
    }
    public record UserDetails(String email, String firstName, String lastName) {
        public UserDetails {
            // If email is null, set it to the default email
            email = email != null ? email : "testUser@gmail.com";
            firstName = firstName != null ? firstName : "test1";
            lastName = lastName != null ? lastName : "user1";
        }
    }

}