package org.example;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void createOrder(Order order) {
        // Business logic could go here, then we save:
        orderDao.saveOrder(order);
    }
}