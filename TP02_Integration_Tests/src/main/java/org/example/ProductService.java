package org.example;

public class ProductService {
    private final ProductApiClient productApiClient;

    public ProductService(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    public Product getProduct(String productId) {
        // In a real application, we might add validation or caching here
        return productApiClient.getProduct(productId);
    }
}