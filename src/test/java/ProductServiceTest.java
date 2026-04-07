import org.example.Product;
import org.example.ProductApiClient;
import org.example.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductApiClient productApiClient; // Mocking the external API

    @InjectMocks
    private ProductService productService; // Injecting the mock into our service

    @Test
    public void testGetProduct_Success() {
        // Scenario 1: Successful retrieval 
        String targetId = "PROD-001";
        Product mockProduct = new Product(targetId, "Laptop");

        when(productApiClient.getProduct(targetId)).thenReturn(mockProduct);

        Product result = productService.getProduct(targetId);

        assertEquals("Laptop", result.getName(), "Should return the correct product name");
        verify(productApiClient).getProduct(targetId); // Verify correct argument [cite: 46]
    }

    @Test
    public void testGetProduct_ApiFailure() {
        // Scenario 2: API Call Failure (e.g., 500 Internal Server Error) 
        String targetId = "PROD-002";

        // We tell the mock to simulate a server crash by throwing an exception 
        when(productApiClient.getProduct(targetId))
                .thenThrow(new RuntimeException("500 Internal Server Error: API is down"));

        // We assert that calling our service actually results in this exception bubbling up
        assertThrows(RuntimeException.class, () -> {
            productService.getProduct(targetId);
        }, "Should throw an exception when API fails");

        verify(productApiClient).getProduct(targetId);
    }

    @Test
    public void testGetProduct_IncompatibleDataFormat() {
        // Scenario 3: Incompatible Data Format (e.g., API returns malformed JSON) 
        String targetId = "PROD-003";

        // We simulate a parsing error
        when(productApiClient.getProduct(targetId))
                .thenThrow(new IllegalArgumentException("400 Bad Request: Invalid Data Format"));

        assertThrows(IllegalArgumentException.class, () -> {
            productService.getProduct(targetId);
        }, "Should throw an exception for incompatible data format");

        verify(productApiClient).getProduct(targetId);
    }
}