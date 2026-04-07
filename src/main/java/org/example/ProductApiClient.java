package org.example;

public interface ProductApiClient {
    // This simulates an external HTTP call (e.g., GET /api/products/{id})
    Product getProduct(String productId);
}