import org.example.Order;
import org.example.OrderController;
import org.example.OrderDao;
import org.example.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderDao orderDao; // We mock the database layer so we don't need a real DB



    private OrderService orderService;
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        // We wrap the real OrderService in a Spy. 
        // This lets it run real code but also lets Mockito track it.
        orderService = Mockito.spy(new OrderService(orderDao));

        // We inject the spied service into our controller
        orderController = new OrderController(orderService);
    }

    @Test
    public void testCreateOrder() {
        // Arrange: Create a test order
        Order myOrder = new Order("ORD-123", 250.50);

        // Act: Call the controller method
        orderController.createOrder(myOrder);

        // Assert: Verify the interactions requested by the TP
        // 1. Verify the service was called with the correct argument
        verify(orderService).createOrder(myOrder);

        // 2. Verify the DAO was called with the correct argument
        verify(orderDao).saveOrder(myOrder);
    }
}